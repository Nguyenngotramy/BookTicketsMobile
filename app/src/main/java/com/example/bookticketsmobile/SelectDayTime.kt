package com.example.bookticketsmobile

import android.app.AlertDialog
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.bookticketsmobile.Adapter.CustomGridView
import com.example.bookticketsmobile.Adapter.RvAdapter
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.databinding.ActivitySelectDayTimeBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory

class SelectDayTime : AppCompatActivity(), RvAdapter.OnItemClickListener {
    private lateinit var myList: MutableList<String>
    private lateinit var binding: ActivitySelectDayTimeBinding
    private lateinit var btViewModel: bookTicketViewModel
    private var selectedIdCumRap: Int? = null
    private var idS: Int? = null
    private var thoiLuong: Long? = null
//    private var imageBytes: ImageBytes? = null
    private var nameMovie: String? = null
    private lateinit var dateAdapter: RvAdapter
    private lateinit var customerAdapter: CustomGridView
    private var date: String? = null
    private var imageBytes: ByteArray? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectDayTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()
        setupRecyclerView()
        observeViewModel()


        binding.NameCinameClusterPickerButton.setOnClickListener {
            initCinemaClusterPicker()
        }

        val i = intent
        idS = i.getIntExtra("id", 0)
        nameMovie = i.getStringExtra("name") ?: "Unknown Film"
        val category = i.getStringExtra("category") ?: "Unknown Category"
        val mota = i.getStringExtra("mote") ?: "Unknown Description"
        date = i.getStringExtra("date") ?: "Unknown Date"
        val imageBytes = i.getByteArrayExtra("image_bytes")
        val thoiLuong = i.getLongExtra("thoiLuong", 0)

        binding.nameFilmDay.text = nameMovie
        binding.txtcategory.text = category

        if (imageBytes != null) {
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
            binding.imgFilmD.setImageBitmap(bitmap)
            binding.detailImg1.setImageBitmap(bitmap)
        }

        if (thoiLuong > 0) {
            val durationInMinutes: Long = thoiLuong
            val formattedTime: String = durationInMinutes.toFormattedTime()
            binding.txtThoiLuong.text = formattedTime
        } else {
            binding.txtThoiLuong.text = "0"
        }
        setupListView()
    }

    private fun initCinemaClusterPicker() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_cinamecluster_picker, null)
        val spinner: Spinner = dialogView.findViewById(R.id.spinnercinameCluser)

        var cinemaClusterData: List<Pair<Int, String>> = emptyList()

        btViewModel.getAllCinameCluster().observe(this, { cinemaClusters ->
            cinemaClusterData = cinemaClusters.map { Pair(it.idCumRap, it.tenCumRap!!) }
            val cinemaClusterNames = cinemaClusterData.map { it.second }
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cinemaClusterNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        })

        val alertDialog = AlertDialog.Builder(this)
            .setTitle("Choose Cinema Cluster")
            .setView(dialogView)
            .setPositiveButton("OK") { dialog, which ->
                val selectedPosition = spinner.selectedItemPosition
                val selectedCinemaCluster = cinemaClusterData[selectedPosition]
                selectedIdCumRap = selectedCinemaCluster.first
                binding.NameCinameClusterPickerButton.text = selectedCinemaCluster.second
                refreshDateList()
            }
            .setNegativeButton("Cancel", null)
            .create()

        alertDialog.show()
    }

    private fun setupViewModel() {
        val repository = BookTicketsRepository(BookTicketsDatabase(this))
        val viewModelFactory = bookTicketViewModelFactory(application, repository)
        btViewModel = ViewModelProvider(this, viewModelFactory).get(bookTicketViewModel::class.java)
    }

    fun Long.toFormattedTime(): String {
        val hours = this / 60
        val minutes = this % 60
        return "${hours}h ${minutes}m"
    }

    private fun setupRecyclerView() {
        dateAdapter = RvAdapter(this, this) // Truyền tham số this vào adapter
        binding.dayList.apply {
            layoutManager = LinearLayoutManager(this@SelectDayTime, LinearLayoutManager.HORIZONTAL, false)
            adapter = dateAdapter
        }
    }

    private fun observeViewModel() {
        if (idS != null && selectedIdCumRap != null) {
            btViewModel.getAllDateByPhim(idS!!, selectedIdCumRap!!).observe(this) { mv ->
                val dayList = mv.map { it }
                dateAdapter.submitList(dayList)
                updateUI(mv)
            }
        }
    }

    private fun updateUI(mv: List<DayData>) {
        binding.dayList.visibility = if (mv.isNotEmpty()) View.VISIBLE else View.GONE
    }

    private fun refreshDateList() {
        observeViewModel()
    }

    private fun setupListView() {
        if (idS != null && selectedIdCumRap != null && date != null) {
            customerAdapter = CustomGridView(this, emptyList(),  0, "Doraemon", 120)
            binding.timeList.adapter = customerAdapter
            btViewModel.getAllTgcByid(idS!!, selectedIdCumRap!!, date!!).observe(this) { foods ->
                customerAdapter.refreshData(foods)
            }
        }
    }

    override fun onItemClick(date: String) { // Hàm triển khai từ interface
        this.date = date
        updateDataAndDisplay(date)
        setupListView() // Call this to update the list view with the new date
    }

    fun updateDataAndDisplay(newData: String) {
        if (!::myList.isInitialized) {
            myList = mutableListOf()
        }
        myList.add(newData)
        displayNewInfo(newData)
    }

    private fun displayNewInfo(newData: String) {

        val textView: TextView = findViewById(R.id.textView12)
        textView.text = newData
    }
}
