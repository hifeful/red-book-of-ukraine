package com.hifeful.redbookofukraine.ui.map

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.clustering.ClusterManager
import com.google.maps.android.data.geojson.GeoJsonLayer
import com.hifeful.redbookofukraine.R
import com.hifeful.redbookofukraine.databinding.FragmentMapBinding
import com.hifeful.redbookofukraine.domain.Organism
import com.hifeful.redbookofukraine.domain.toLatLng
import com.hifeful.redbookofukraine.util.OrganismClusterItem
import com.hifeful.redbookofukraine.util.OrganismClusterRenderer
import com.hifeful.redbookofukraine.util.createUserBitmap
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMapBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapFragment = childFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        observeOrganisms()
        mViewModel.getAllOrganisms()
    }

    private fun observeOrganisms() {
        mViewModel.organisms.observe(viewLifecycleOwner, { organisms ->
            organisms.forEach { addOrganismToMap(it) }
        })
    }

    override fun onMapReady(p0: GoogleMap) {
        mMap = p0
        setUpMap()
    }

    private fun setUpMap() {
        mMap.uiSettings.isZoomControlsEnabled = true
        setUpCluster()
        moveCamera(50.4501, 30.5234)

        highlightUkraineBoundaries()
    }

    private fun addOrganismToMap(organism: Organism) {
        for (coordinates in organism.coordinates) {
            val organismClusterItem = OrganismClusterItem(coordinates, organism.photoUrl)
            mClusterManager.addItem(organismClusterItem)

//            val options = MarkerOptions().position(position)
//            createUserBitmap(requireContext(), options, organism.photoUrl, ::displayMarker)
        }
    }

    private fun displayMarker(options: MarkerOptions, bitmap: Bitmap) {
        options.icon(BitmapDescriptorFactory.fromBitmap(bitmap))
        options.anchor(0.5f, 0.907f)

        mMap.addMarker(options)
    }

    private fun moveCamera(latitude: Double, longitude: Double) {
        val coordinates = LatLng(latitude, longitude)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(coordinates, 5F))
    }

    @SuppressLint("PotentialBehaviorOverride")
    private fun setUpCluster() {
        mClusterManager = ClusterManager(requireContext(), mMap)
        mClusterManager.renderer = OrganismClusterRenderer(requireContext(), mMap, mClusterManager)

        mMap.setOnCameraIdleListener(mClusterManager)
        mMap.setOnMarkerClickListener(mClusterManager)
    }

    private fun highlightUkraineBoundaries() {
        val layer = GeoJsonLayer(mMap, R.raw.ukraine_geo, context)

        val style = layer.defaultPolygonStyle
        style.strokeColor = Color.RED
        style.strokeWidth = 3F

        layer.addLayerToMap()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}