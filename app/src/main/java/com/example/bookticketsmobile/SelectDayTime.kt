package com.example.bookticketsmobile

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class SelectDayTime : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_day_time)
        // List ngày
        val dayList = mutableListOf<DayData>()
        dayList.add(DayData("10/6/2024"))
        dayList.add(DayData("11/6/2024"))
        dayList.add(DayData("12/6/2024"))
        dayList.add(DayData("13/6/2024"))
        dayList.add(DayData("14/6/2024"))
        dayList.add(DayData("15/6/2024"))
        dayList.add(DayData("16/6/2024"))

        val adapter = RvAdapter(dayList)
        var list1 = findViewById<RecyclerView>(R.id.dayList)
        list1.adapter = adapter
        list1.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        // List giờ
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

        val customGV = CustomGridView(this, timeList)
        var list2 = findViewById<GridView>(R.id.timeList)
        list2.adapter = customGV

    }
}