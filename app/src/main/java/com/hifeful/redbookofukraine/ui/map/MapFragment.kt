package com.hifeful.redbookofukraine.ui.map

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.util.DisplayMetrics
import android.util.Log
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.clustering.algo.NonHierarchicalViewBasedAlgorithm
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.hifeful.redbookofukraine.R
import com.hifeful.redbookofukraine.databinding.FragmentMapBinding
import com.hifeful.redbookofukraine.domain.Organism
import com.hifeful.redbookofukraine.ui.adapters.CustomSearchAdapter
import com.hifeful.redbookofukraine.ui.view.MaterialSearchView
import com.hifeful.redbookofukraine.util.OrganismClusterItem
import com.hifeful.redbookofukraine.util.OrganismClusterRenderer
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MapFragment : DaggerFragment(), OnMapReadyCallback {
    private val TAG = MapFragment::class.qualifiedName

    @Inject
    lateinit var mProviderFactory: ViewModelProvider.Factory
    private val mViewModel by viewModels<MapViewModel> { mProviderFactory }

    private var _binding: FragmentMapBinding? = null
    private val binding get() = _binding!!

    // UI
    private lateinit var mMap: GoogleMap
    private lateinit var mClusterManager: ClusterManager<OrganismClusterItem>

    private lateinit var mSearchAdapter: CustomSearchAdapter
    private var mIsKeyboardShowing = false

    private var isFirstSetUp = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        setHasOptionsMenu(true)
        setUpToolbar()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment?

        if (isFirstSetUp) {
            mapFragment?.getMapAsync(this)
            isFirstSetUp = false
        }
    }

    private fun setUpToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.homeToolbar)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.menu_home, menu)
        val searchItem = menu.findItem(R.id.action_search)
        setUpSearch(searchItem)

        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.action_search -> {
//                mSearchView.openSearch()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    private fun setUpSearch(searchItem: MenuItem) {
        mViewModel.isSearchVisible.observe(viewLifecycleOwner, { isVisible ->
            binding.homeBackgroundBlur.isVisible = isVisible
            Log.d(TAG, "setUpSearch: ${binding.homeSearchView.isSearchOpen}")
        })

        mSearchAdapter =
            CustomSearchAdapter(
                requireContext(), arrayOf(),
                ContextCompat.getDrawable(requireContext(), R.drawable.ic_baseline_search_24),
                true
            )
        binding.homeSearchView.apply {
            setMenuItem(searchItem)
            setAdapter(mSearchAdapter)
            setOnSearchViewListener(object : MaterialSearchView.SearchViewListener {
                override fun onSearchViewClosed() {
                    binding.homeSearchView.isVisible = false
                    binding.homeToolbar.isVisible = true

                    mViewModel.setSearchVisible(false)
                }

                override fun onSearchViewShown() {
                    binding.homeSearchView.isVisible = true
                    binding.homeToolbar.isVisible = false

                    mViewModel.setSearchVisible(true)
                }
            })
            setOnQueryTextListener(object : MaterialSearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    return false
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    newText?.let {
                        dismissSuggestions()
                        mViewModel.searchOrganismsShort(it)
                    }

                    return false
                }
            })
            setOnItemClickListener { _, _, _, _ ->
                setViewRequested(mIsKeyboardShowing)
                val action =
                    MapFragmentDirections.actionMapFragmentToSearchFragment(mViewModel.searchQuery)
                findNavController().navigate(action)
                clearFocus()
            }
        }

        mViewModel.searchSuggestions.observe(viewLifecycleOwner, { suggestions ->
            binding.homeSearchView.setSuggestions(suggestions.map { it.name }.toTypedArray())
        })
    }

    private fun observeOrganisms() {
        mViewModel.organisms.observe(viewLifecycleOwner, { organisms ->
            Log.i(TAG, "observeOrganisms: ${organisms.size}")
            organisms.forEach { addOrganismToMap(it) }
        })
    }

    override fun onMapReady(p0: GoogleMap?) {
        mMap = p0 ?: return
        setUpMap()
    }

    private fun setUpMap() {
        mMap.clear()
        mMap.uiSettings.isZoomControlsEnabled = true
        moveCamera(50.4501, 30.5234)

        highlightUkraineBoundaries()
        setUpCluster()
        observeOrganisms()
    }

    private fun addOrganismToMap(organism: Organism) {
        for (coordinates in organism.coordinates) {
            val organismClusterItem = OrganismClusterItem(coordinates, organism)
            mClusterManager.addItem(organismClusterItem)
        }
        mClusterManager.cluster()
    }

    private fun moveCamera(latitude: Double, longitude: Double) {
        val coordinates = LatLng(latitude, longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 5F))
    }

    @SuppressLint("PotentialBehaviorOverride")
    private fun setUpCluster() {
        mClusterManager = ClusterManager(requireContext(), mMap)
        with(getDisplayMetrics()) {
            mClusterManager.setAlgorithm(NonHierarchicalViewBasedAlgorithm<OrganismClusterItem>(widthPixels, heightPixels))
        }

        mMap.setOnCameraIdleListener(mClusterManager)
        mClusterManager.renderer = OrganismClusterRenderer(requireContext(), mMap, mClusterManager)
        mClusterManager.setOnClusterItemClickListener {
            Log.i(TAG, "setUpCluster: onClusterItemClick")
            val action = MapFragmentDirections.actionMapFragmentToOrganismFragment(it.organism)
            findNavController().navigate(action)
            true
        }
        mMap.setOnMarkerClickListener(mClusterManager)

    }

    private fun highlightUkraineBoundaries() {
        val layer = GeoJsonLayer(mMap, R.raw.ukraine_geo, context)

        val style = layer.defaultPolygonStyle
        style.strokeColor = Color.RED
        style.strokeWidth = 3F

        layer.addLayerToMap()
    }

    private fun getDisplayMetrics(): DisplayMetrics =
        requireContext().resources.displayMetrics

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}