package com.example.bookticketsmobile

import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bookticketsmobile.databinding.ActivitySelectDayTimeBinding

class SelectDayTime : AppCompatActivity() {

    private lateinit var binding: ActivitySelectDayTimeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySelectDayTimeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            val nameMovie = bundle.getString("nameMovieS")
            val category = bundle.getString("categoryS")
            val mota = bundle.getString("DecriS")
            val thoiLuong = bundle.getLong("thoiLuongS")
            val dateOut = bundle.getString("DateOutS")
            val imgUriString = bundle.getString("imgUriS")


            binding.nameFilmDay.text = nameMovie
            binding.txtcategory.text = category

            if (thoiLuong > 0) {
                val durationInMinutes: Long = thoiLuong
                val formattedTime: String = durationInMinutes.toFormattedTime()
                binding.txtThoiLuong.text = formattedTime
            } else {
                binding.txtThoiLuong.text = "0"

                imgUriString?.let {
                    val imgUri = Uri.parse(it)
                    Glide.with(this)
                        .load(imgUri)
                        .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
                        .error(R.drawable.baseline_image_24) // Image to show if loading fails
                        .into(binding.detailImg1)
                    Glide.with(this)
                        .load(imgUri)
                        .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
                        .error(R.drawable.baseline_image_24) // Image to show if loading fails
                        .into(binding.imgFilmD)
                }
            }

            // Setup RecyclerView for days list
            val dayList = mutableListOf<DayData>()
            dayList.add(DayData("10/6/2024"))
            dayList.add(DayData("11/6/2024"))
            dayList.add(DayData("12/6/2024"))
            dayList.add(DayData("13/6/2024"))
            dayList.add(DayData("14/6/2024"))
            dayList.add(DayData("15/6/2024"))
            dayList.add(DayData("16/6/2024"))

            val adapter = RvAdapter(dayList)
            val list1 = findViewById<RecyclerView>(R.id.dayList)
            list1.adapter = adapter
            list1.layoutManager = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
            )

            // Setup RecyclerView for times list
            val timeList = mutableListOf<TimeData>()
            timeList.add(TimeData("11:00"))
            timeList.add(TimeData("12:00"))
            timeList.add(TimeData("13:00"))
            timeList.add(TimeData("14:00"))
            timeList.add(TimeData("15:00"))
            timeList.add(TimeData("16:00"))
            timeList.add(TimeData("17:00"))
            timeList.add(TimeData("18:00"))
            timeList.add(TimeData("19:00"))

            // Uncomment below lines if you have a custom GridView adapter
            /* val customGV = CustomGridView(this, timeList, idFilm, imgFilm, nameFilm)
        val list2 = findViewById<GridView>(R.id.timeList)
        list2.adapter = customGV */
        }
    }
        fun Long.toFormattedTime(): String {
            val hours = this / 60
            val minutes = this % 60
            return "${hours}h ${minutes}m"
        }
    }
