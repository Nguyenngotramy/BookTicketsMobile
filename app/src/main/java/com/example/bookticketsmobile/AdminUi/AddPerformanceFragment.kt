package com.example.bookticketsmobile.AdminUi

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
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
import com.example.bookticketsmobile.Model.suatChieu
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FragmentAddPerformanceBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory
import java.util.Calendar
private var _binding: FragmentAddPerformanceBinding? = null
private val binding get() = _binding!!
private var datePickerDialog: DatePickerDialog? = null
private var timePickerDialog: TimePickerDialog? = null
private lateinit var btViewModel: bookTicketViewModel
private var  selectedIdFilm:Int ?= null
private var  selectedIdCumRap:Int ?= null
class AddPerformanceFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
     _binding = FragmentAddPerformanceBinding.inflate(inflater, container, false)
        setupViewModel()
        initDatePicker()
        initTimePicker()
        binding.datePickerButton.setOnClickListener { 
            openDatePicker()
        }
        binding.timePickerButton.setOnClickListener { 
           openTimePicker()
        }
        binding.NameCinameClusterPickerButton.setOnClickListener { 
            initCinameClusterPicker()
        }
        binding.FilmPickerButton.setOnClickListener {
            initFilmPicker()
        }
        binding.btnAdd.setOnClickListener {
            add()
        }
     return binding.root
    }
    private fun setupViewModel() {
        val btReposition = BookTicketsRepository(BookTicketsDatabase(requireContext()))
        val viewModelProviderFactory = bookTicketViewModelFactory(requireActivity().application,btReposition)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }

    private fun initDatePicker() {
        val dateSetListener =
            DatePickerDialog.OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date = makeDateString(day, month, year)
                binding.datePickerButton.setText(date)
            }
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        val style: Int = AlertDialog.THEME_HOLO_LIGHT
        datePickerDialog = DatePickerDialog(requireContext(), style, dateSetListener, year, month, day)

    }

    private fun makeDateString(day: Int, month: Int, year: Int): String? {
        return getMonthFormat(month) + " " + day + " " + year
    }

    private fun getMonthFormat(month: Int): String {
        if (month == 1) return "JAN"
        if (month == 2) return "FEB"
        if (month == 3) return "MAR"
        if (month == 4) return "APR"
        if (month == 5) return "MAY"
        if (month == 6) return "JUN"
        if (month == 7) return "JUL"
        if (month == 8) return "AUG"
        if (month == 9) return "SEP"
        if (month == 10) return "OCT"
        if (month == 11) return "NOV"
        return if (month == 12) "DEC" else "JAN"
    }

    fun openDatePicker() {
        datePickerDialog?.show()
    }

    private fun initTimePicker() {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
            val time = makeTimeString(hourOfDay, minute)
            binding.timePickerButton.text = time
        }
        val cal: Calendar = Calendar.getInstance()
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
        val minute: Int = cal.get(Calendar.MINUTE)
        val style: Int = AlertDialog.THEME_HOLO_LIGHT
        timePickerDialog = TimePickerDialog(requireContext(), style, timeSetListener, hour, minute, true)
    }

    private fun makeTimeString(hourOfDay: Int, minute: Int): String {
        return String.format("%02d:%02d", hourOfDay, minute)
    }
    fun openTimePicker() {
        timePickerDialog?.show()
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
    private fun initFilmPicker() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_film_picker, null)
        val spinner: Spinner = dialogView.findViewById(R.id.spinner_film)
        var filmData: List<Pair<Int, String?>> = listOf()
        btViewModel.getAllFilm().observe(viewLifecycleOwner, { cinameClusterList ->
            filmData = cinameClusterList.map { Pair(it.idPhim, it.tenBoPhim) }
            val cinameClusterNames = filmData.map { it.second }
            val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, cinameClusterNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        })

        val alertDialog = AlertDialog.Builder(requireContext())
            .setTitle("Name Movie")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->
                val selectedPosition = spinner.selectedItemPosition
                val selectedfilm = filmData[selectedPosition]
                val selectedName = selectedfilm.second
                selectedIdFilm = selectedfilm.first

                binding.FilmPickerButton.text = selectedName

            }
            .setNegativeButton("Cancel", null)
            .create()

        alertDialog.show()
    }
    private fun add(){
        val showTime = binding.timePickerButton.text.toString().trim()
        val cinameRoom = binding.txtCinameRoom.text.toString().trim()
        val showDate = binding.datePickerButton.text.toString().trim()
        val numofSeat = binding.txtNumSeat.text.toString().trim()
        val  numseat:Int = numofSeat.toInt()
        val idF = selectedIdFilm
        val idC = selectedIdCumRap
        if ( showDate.isNotEmpty() && showTime.isNotEmpty() && cinameRoom.isNotEmpty() && idF != null && idC != null){
            val sc = suatChieu(0,showDate,showTime,cinameRoom,numseat,idF, idC)
            btViewModel.addPerformance(sc)
            Toast.makeText(requireContext(),"add success", Toast.LENGTH_SHORT).show()
            clean()
        }else {
            Toast.makeText(requireContext(),"fill out the form", Toast.LENGTH_SHORT).show()
        }
    }
    private fun clean(){
        binding.NameCinameClusterPickerButton.setText("Name ciname cluster")
        binding.timePickerButton.setText("show Time")
        binding.txtCinameRoom.setText("")
        binding.txtNumSeat.setText("")
        binding.datePickerButton.setText("show Date")
        binding.FilmPickerButton.setText("Name Movie")
    }

}

