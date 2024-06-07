package com.example.bookticketsmobile.AdminUi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.bookticketsmobile.Adapter.ListFilmAdapter
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FragmentListMoviesBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory

class List_Movies_Fragment : Fragment(R.layout.list_movies_ad), SearchView.OnQueryTextListener {
    private var _binding: FragmentListMoviesBinding? = null
    private val binding get() = _binding!!
    private lateinit var btViewModel: bookTicketViewModel
    private lateinit var filmAdapter: ListFilmAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupRecyclerView()
        observeViewModel()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupViewModel() {
        val btRepository = BookTicketsRepository(BookTicketsDatabase(requireContext()))
        val viewModelProviderFactory = bookTicketViewModelFactory(requireActivity().application, btRepository)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }

    private fun setupRecyclerView() {
        filmAdapter = ListFilmAdapter()
        binding.listMoviesAd.apply {
            layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            adapter = filmAdapter
        }
    }

    private fun observeViewModel() {
        btViewModel.getAllFilm().observe(viewLifecycleOwner) { mv ->
            filmAdapter.differ.submitList(mv)
            updateUI(mv)
        }
    }

    private fun updateUI(mv: List<Phim>) {
        binding.listMoviesAd.visibility = if (mv.isNotEmpty()) View.VISIBLE else View.GONE
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        return false
    }
}
