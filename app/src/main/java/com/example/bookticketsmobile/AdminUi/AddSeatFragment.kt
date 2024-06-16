package com.example.bookticketsmobile.AdminUi

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.Model.choNgoi
import com.example.bookticketsmobile.Model.suatChieuDetail
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FragmentAddSeatBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory

class AddSeatFragment : Fragment() {

    private var _binding: FragmentAddSeatBinding? = null
    private val binding get() = _binding!!
    private lateinit var btViewModel: bookTicketViewModel
    private var selectedIdsuatChieu: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddSeatBinding.inflate(inflater, container, false)
        setupViewModel()
        binding.PerformancePickerButton.setOnClickListener { initPerformancePicker() }
        binding.btnAdd.setOnClickListener{
            addSeat()
        }
        return binding.root
    }

    private fun setupViewModel() {
        val btRepository = BookTicketsRepository(BookTicketsDatabase(requireContext()))
        val viewModelProviderFactory = bookTicketViewModelFactory(requireActivity().application, btRepository)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }

    private fun initPerformancePicker() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_performance_picker, null)
        val spinner: Spinner = dialogView.findViewById(R.id.spinner_performance)

        btViewModel.getAllPerformance().observe(viewLifecycleOwner, { scList ->
            val sc = scList.map { it.toDisplayString() }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, sc)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            val alertDialog = AlertDialog.Builder(requireContext())
                .setTitle("Performance")
                .setView(dialogView)
                .setPositiveButton("OK") { dialog, which ->
                    val selectedPosition = spinner.selectedItemPosition
                    if (selectedPosition >= 0) {
                        val selectedPerformance = scList[selectedPosition]
                        selectedIdsuatChieu = selectedPerformance.idSuatChieu
                        binding.PerformancePickerButton.text = selectedPerformance.toDisplayString()
                        binding.txtDateshow.setText(selectedPerformance.toDisplayString1())
                    } else {
                        Toast.makeText(requireContext(), "No performance selected", Toast.LENGTH_SHORT).show()
                    }
                }
                .setNegativeButton("Cancel", null)
                .create()

            alertDialog.show()
        })
    }

    private fun suatChieuDetail.toDisplayString(): String {
        return "$tenCumRap-$tenBoPhim"
    }
    private fun suatChieuDetail.toDisplayString1(): String {
        return "Performance: \nCiname cluster: $tenCumRap\nMovie: $tenBoPhim\nDate show:$ngayChieu\nTime show:$thoiGianChieu"
    }


    private fun addSeat() {
       val nameSeat = binding.txttenHangGhe.text.toString()
        val priceStr = binding.txtPriceSeat.text.toString()
        val price :Double = priceStr.toDouble()
        val idSc = selectedIdsuatChieu
        if (nameSeat.isNotEmpty() && price != null && idSc != null ) {
                val s = choNgoi(0, nameSeat, price, idSc, null)
                btViewModel.addSeat(s)
                Toast.makeText(requireContext(), "Add success", Toast.LENGTH_SHORT).show()
                clean()
        } else{
            Toast.makeText(requireContext(),"Please fill out again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun clean() {
      binding.txttenHangGhe.setText("")
        binding.txtDateshow.setText("")
        binding.txtPriceSeat.setText("")
        binding.PerformancePickerButton.setText("Performance")
    }
}
