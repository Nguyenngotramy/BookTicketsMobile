package com.example.bookticketsmobile.AdminUi

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.Adapter.ListPerformanceAdapter
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FragmentListPerformanceBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory


class ListPerformanceFragment : Fragment(R.layout.fragment_list_performance), SearchView.OnQueryTextListener {
    private var _binding: FragmentListPerformanceBinding? = null
    private val binding get() = _binding!!
    private lateinit var btViewModel: bookTicketViewModel
    private lateinit var customerAdapter: ListPerformanceAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListPerformanceBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupListView()
        /*  setupSearchView()*/
    }

    private fun setupViewModel() {
        val btRepository = BookTicketsRepository(BookTicketsDatabase(requireContext()))
        val viewModelProviderFactory = bookTicketViewModelFactory(requireActivity().application, btRepository)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }

    private fun setupListView() {

        customerAdapter = ListPerformanceAdapter(requireActivity(), emptyList())

        binding.listperformance.adapter = customerAdapter
        btViewModel.getAllPerformance().observe(viewLifecycleOwner) { p->
            customerAdapter.refreshData(p)
        }
    }

    /*  private fun setupSearchView() {
          binding.searchView.setOnQueryTextListener(re)
      }*/

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        /* newText?.let {
             val filteredList = btViewModel.getAllCustomers().value?.filter { customer ->
                 customer.hoVaTen!!.contains(it, ignoreCase = true)
             } ?: emptyList()
             customerAdapter.refreshData(filteredList)
         }*/
        return false
    }
}
