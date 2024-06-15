package com.example.bookticketsmobile

import android.os.Bundle
import android.widget.GridView
import androidx.appcompat.app.AppCompatActivity

class SelectSeat : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_seat)

        var list = mutableListOf<SeatData>()
        list.add(SeatData(1, "A1"))
        list.add(SeatData(2, "A2"))
        list.add(SeatData(3, "A3"))
        list.add(SeatData(4, "A4"))
        list.add(SeatData(5, "A5"))
        list.add(SeatData(6, "A6"))
        list.add(SeatData(7, "A7"))
        list.add(SeatData(8, "A8"))
        list.add(SeatData(9, "B1"))
        list.add(SeatData(10, "B2"))
        list.add(SeatData(12, "B3"))
        list.add(SeatData(13, "A1"))
        list.add(SeatData(14, "A2"))
        list.add(SeatData(15, "A3"))
        list.add(SeatData(16, "A4"))
        list.add(SeatData(17, "A5"))
        list.add(SeatData(18, "A6"))
        list.add(SeatData(19, "A7"))
        list.add(SeatData(20, "A8"))
        list.add(SeatData(21, "B1"))
        list.add(SeatData(22, "B2"))
        list.add(SeatData(23, "B3"))

        val customGV = CustomGridViewSeat(this, list)
        var seatList = findViewById<GridView>(R.id.seatList)
        seatList.adapter = customGV


    }
}