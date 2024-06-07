package com.example.bookticketsmobile.AdminUi

import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.Model.cumRap
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FragmentAddCinemaClustersBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory

class AddCinemaClustersFragment : Fragment() {
    private  var _binding : FragmentAddCinemaClustersBinding?=null
    private val binding get() = _binding!!

    private lateinit var btViewModel:bookTicketViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddCinemaClustersBinding.inflate(inflater, container, false)
        binding.provinceCityPickerButton.setOnClickListener {
            initProvincePicker()
        }
        setupViewModel()
        binding.btnAddd.setOnClickListener {
            addCinameClusters()
        }
        return  binding.root
    }
    private fun initProvincePicker() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_province_picker, null)
        val spinnerProvince: Spinner = dialogView.findViewById(R.id.spinner_province)

        val adapter = ArrayAdapter.createFromResource(
            requireContext(),
            R.array.province_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinnerProvince.adapter = adapter

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Chọn tỉnh/thành")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->
                val selectedProvince = spinnerProvince.selectedItem.toString()
                binding.provinceCityPickerButton.text = selectedProvince
            }
            .setNegativeButton("Cancel", null)
            .create()

        alertDialog.show()
    }

    private fun addCinameClusters(){
        val provinceCity = binding.provinceCityPickerButton.text.toString().trim()
        val cinemaClustersName = binding.txtCinameClustersName.text.toString().trim()
        if (provinceCity.isNotEmpty()&& cinemaClustersName.isNotEmpty()){
            val cr = cumRap(0,provinceCity,cinemaClustersName)
            btViewModel.addCinameClusters(cr)
            Toast.makeText(requireContext(), "Add success", Toast.LENGTH_SHORT).show()
            Clean()
        }
    }
    private fun setupViewModel() {
        val btReposition = BookTicketsRepository(BookTicketsDatabase(requireContext()))
        val viewModelProviderFactory = bookTicketViewModelFactory(requireActivity().application,btReposition)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }

    private fun Clean(){
        binding.provinceCityPickerButton.setText("Province/City")
        binding.txtCinameClustersName.setText("")
    }

}