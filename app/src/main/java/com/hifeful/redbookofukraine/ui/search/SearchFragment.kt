package com.hifeful.redbookofukraine.ui.search

import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.hifeful.redbookofukraine.R
import com.hifeful.redbookofukraine.databinding.FragmentSearchBinding
import com.hifeful.redbookofukraine.domain.Organism
import com.hifeful.redbookofukraine.ui.adapters.OrganismAdapter
import com.hifeful.redbookofukraine.ui.map.MapFragmentDirections
import com.hifeful.redbookofukraine.util.surroundWithQuotes
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class SearchFragment : DaggerFragment() {

    @Inject
    lateinit var mProviderFactory: ViewModelProvider.Factory
    private val mViewModel by viewModels<SearchViewModel> { mProviderFactory }

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val mArgs: SearchFragmentArgs by navArgs()

    private lateinit var mOrganismAdapter: OrganismAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        setHasOptionsMenu(true)
        setUpToolbar()

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpOrganismRecycler()

        observeSearchResults()
    }

    private fun setUpToolbar() {
        (activity as AppCompatActivity).setSupportActionBar(binding.searchToolbar)
        binding.searchToolbar.title = mArgs.query.surroundWithQuotes()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.map_search, menu)
        val searchItem = menu.findItem(R.id.action_search)
        val searchView = searchItem.actionView as SearchView
        setUpSearchView(searchView)

        super.onCreateOptionsMenu(menu, inflater)
    }

    private fun setUpSearchView(searchView: SearchView) {
        if (mViewModel.getSearchQuery() == null) {
            mViewModel.setSearchQuery(mArgs.query)
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    mViewModel.setSearchQuery(query)
                    mViewModel.searchOrganisms(query)
                    binding.searchToolbar.title = query.surroundWithQuotes()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        searchView.setQuery(mViewModel.getSearchQuery(), true)
    }

    private fun setUpOrganismRecycler() {
        mOrganismAdapter = OrganismAdapter().apply {
            mOnOrganismClickListener = object : OrganismAdapter.OnOrganismClickListener {
                override fun onoOrganismClick(organism: Organism) {
                    val action =
                        SearchFragmentDirections.actionSearchFragmentToOrganismFragment(organism)
                    findNavController().navigate(action)
                }

            }
        }

        binding.organismsRecycler.apply {
            adapter = mOrganismAdapter
            layoutManager = LinearLayoutManager(
                requireContext(),
                LinearLayoutManager.VERTICAL, false
            )
        }
    }

    private fun observeSearchResults() {
        mViewModel.searchResults.observe(viewLifecycleOwner, { searchResults ->
            mOrganismAdapter.mOrganismList = searchResults.toMutableList()
        })
    }
}