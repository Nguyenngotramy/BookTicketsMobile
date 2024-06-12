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
import com.example.bookticketsmobile.Model.CumRap_khuyenMai
import com.example.bookticketsmobile.Model.khuyenMai
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FragmentAddVorcherBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory

class AddVorcherFragment : Fragment() {

    private var _binding:FragmentAddVorcherBinding?=null
    private  val binding get() = _binding !!
    private  lateinit var btViewModel:bookTicketViewModel
    private var  selectedIdVorcher:String ?= null
    private var  selectedIdCumRap:Int ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddVorcherBinding.inflate(inflater,container,false)
        setupViewModel()
        binding.btnAdd.setOnClickListener {
            addVorcher()
        }
        binding.vorcherPickerButton.setOnClickListener {
            initVorcherPicker()
        }
        binding.NameCinameClusterPickerButton.setOnClickListener {
            initCinameClusterPicker()
        }
        binding.btnSaleFood.setOnClickListener {
            addVorcher_CumRap()
        }

       return binding.root
    }
    private fun setupViewModel() {
        val btReposition = BookTicketsRepository(BookTicketsDatabase(requireContext()))
        val viewModelProviderFactory = bookTicketViewModelFactory(requireActivity().application,btReposition)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }

    private fun addVorcher(){
        val idKm = binding.txtIdVorcher.text.toString().trim()
        val nd = binding.txtNdVorcher.text.toString().trim()
        val percent = binding.txtPercent.text.toString()
        val percentD : Double = percent.toDouble()

        if(idKm.isNotEmpty() && nd.isNotEmpty() && percentD != null){
            val vc = khuyenMai(idKm,percentD,nd)
            btViewModel.addVorcher(vc)
            Toast.makeText(requireContext(),"Add sucess",Toast.LENGTH_SHORT).show()
            clean()
        }
    }
    private fun initVorcherPicker() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_vorcher_picker, null)
        val spinner: Spinner = dialogView.findViewById(R.id.spinner_vorcher)

        var vorcher : List< String?> = listOf()
        btViewModel.getAllVorcher().observe(viewLifecycleOwner, { vorcherList ->
            vorcher = vorcherList.map {it.idKM }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, vorcher)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        })

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Id vorcher")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->

                val selectedPosition = spinner.selectedItemPosition
                val selectedvc = vorcher[selectedPosition]
                selectedIdVorcher = selectedvc


                binding.vorcherPickerButton.text = selectedIdVorcher.toString()
            }
            .setNegativeButton("Cancel", null)
            .create()

        alertDialog.show()
    }

    private fun initCinameClusterPicker() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_cinamecluster_picker, null)
        val spinner: Spinner = dialogView.findViewById(R.id.spinnercinameCluser)
        var cinameClusterData: List<Pair<Int, String?>> = listOf()
        btViewModel.getAllCinameCluster().observe(viewLifecycleOwner, { cinameClusterList ->
            cinameClusterData = cinameClusterList.map { Pair(it.idCumRap, it.tenCumRap) }
            val cinameClusterNames = cinameClusterData.map { it.second }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cinameClusterNames)
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

                binding.NameCinameClusterPickerButton.text = selectedName

            }
            .setNegativeButton("Cancel", null)
            .create()

        alertDialog.show()
    }
    private fun clean(){
        binding.txtIdVorcher.setText("")
        binding.txtNdVorcher.setText("")
        binding.txtPercent.setText("")
    }
        private fun addVorcher_CumRap(){
            val idKm = selectedIdVorcher
            val idCr =  selectedIdCumRap
            if(idKm != null && idCr != null){
                val vcr = CumRap_khuyenMai(0,idKm, idCr)
                btViewModel.addVorcher_CumRap(vcr)
                Toast.makeText(requireContext(),"add success", Toast.LENGTH_SHORT).show()
                cleanTXT()
            }
        }

    private fun cleanTXT() {
        binding.NameCinameClusterPickerButton.setText("Name Ciname cluster")
        binding.vorcherPickerButton.setText("Id Vorcher")
    }
}