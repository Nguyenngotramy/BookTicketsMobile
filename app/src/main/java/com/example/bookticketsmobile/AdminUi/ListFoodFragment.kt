package com.example.bookticketsmobile.AdminUi

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.SearchView
import android.widget.Spinner
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.Adapter.ListFoodAdapter
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FragmentListFoodBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory

class ListFoodFragment : Fragment(R.layout.fragment_add_food), SearchView.OnQueryTextListener {
    private var _binding: FragmentListFoodBinding? = null
    private val binding get() = _binding!!
    private lateinit var btViewModel: bookTicketViewModel
    private lateinit var customerAdapter: ListFoodAdapter
    private var selectedIdCumRap: Int? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentListFoodBinding.inflate(inflater, container, false)
        binding.ClusterPickerButton.setOnClickListener {
            initCinameClusterPicker()
        }
        val txt = binding.searchViewFood.text.toString()
        binding.btnfindF.setOnClickListener {
            searchByName(txt)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViewModel()
        setupListView()
    }

    private fun setupViewModel() {
        val btRepository = BookTicketsRepository(BookTicketsDatabase(requireContext()))
        val viewModelProviderFactory =
            bookTicketViewModelFactory(requireActivity().application, btRepository)
        btViewModel =
            ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }

    private fun setupListView() {
        customerAdapter = ListFoodAdapter(requireActivity(), emptyList())
        binding.listfood.adapter = customerAdapter
        btViewModel.getAllFood().observe(viewLifecycleOwner) { foods ->
            customerAdapter.refreshData(foods)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onQueryTextSubmit(query: String?): Boolean {
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        if (!newText.isNullOrBlank()) {
            searchByName(newText)
        } else {
            btViewModel.getAllFood().value?.let { customerAdapter.refreshData(it) }
        }
        return true
    }

    private fun initCinameClusterPicker() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_cinamecluster_picker, null)
        val spinner: Spinner = dialogView.findViewById(R.id.spinnercinameCluser)
        var cinameClusterData: List<Pair<Int, String?>> = listOf()
        btViewModel.getAllCinameCluster().observe(viewLifecycleOwner, { cinameClusterList ->
            cinameClusterData = cinameClusterList.map { Pair(it.idCumRap, it.tenCumRap) }
            val cinameClusterNames = cinameClusterData.map { it.second }
            val adapter = ArrayAdapter(
                requireContext(),
                android.R.layout.simple_spinner_item,
                cinameClusterNames
            )
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        })

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Name ciname cluster")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->
                val selectedPosition = spinner.selectedItemPosition
                val selectedCinameCluster = cinameClusterData[selectedPosition]
                val selectedName = selectedCinameCluster.second
                selectedIdCumRap = selectedCinameCluster.first

                binding.ClusterPickerButton.text = selectedName
            }
            .setNegativeButton("Cancel", null)
            .create()

        alertDialog.show()
    }

    private fun searchByName(query: String) {
        btViewModel.getAllFoodByName(query).observe(viewLifecycleOwner) { list ->
            customerAdapter.refreshData(list)
        }
    }
}
