package com.example.bookticketsmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.GridView
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class SelectSeat : AppCompatActivity(), OnSeatClickListener {
    var seatSelecteds = ArrayList<String>(5)
    var calTotal:Double = 0.0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_seat)


        // lấy dữ liệu từ SelectDayTime
        val i = intent
        val idFilm = i.getIntExtra("idFilm", 0)
        val imgFilm = i.getIntExtra("imageFilm", 0)
        val nameFilm = i.getStringExtra("nameFilm")?: "Unknown Film"
        val time = i.getStringExtra("time")?: "Unknown Time"



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
        list.add(SeatData(11, "B3"))
        list.add(SeatData(12, "A1"))
        list.add(SeatData(13, "A2"))
        list.add(SeatData(14, "A3"))
        list.add(SeatData(15, "A4"))
        list.add(SeatData(16, "A5"))
        list.add(SeatData(17, "A6"))
        list.add(SeatData(18, "A7"))
        list.add(SeatData(19, "A8"))
        list.add(SeatData(20, "B1"))
        list.add(SeatData(21, "B2"))
        list.add(SeatData(22, "B3"))

        val customGV = CustomGridViewSeat(this, list, this)
        var seatList = findViewById<GridView>(R.id.seatList)
        seatList.adapter = customGV

        showSeatSelected()

        var btnNext = findViewById<Button>(R.id.btnNext)
        btnNext.setOnClickListener {
            if(seatSelecteds.size == 0) {
                Toast.makeText(this, "Hãy chọn một vị trí", Toast.LENGTH_SHORT).show()
            } else {
                val i1 = Intent(this, PopcornCombo::class.java)
                i1.putExtra("idFilm", idFilm)
                i1.putExtra("imageFilm", imgFilm)
                i1.putExtra("nameFilm", nameFilm)
                i1.putExtra("time", time)
                i1.putExtra("selectedSeat", seatSelecteds.toTypedArray())
                calTotal = total()
                i1.putExtra("totalSeat", calTotal)
                startActivity(i1)
            }
        }

    }

    override fun onSeatClick(seat: SeatData) {
        var check:Int = 0
        for (seatName in seatSelecteds) {
            if(seat.seatName == seatName) {
                seatSelecteds.remove(seat.seatName)
                check = 1
                showSeatSelected()
                calTotal = total()
                val total = findViewById<TextView>(R.id.totalSeat)
                total.text = calTotal.toString()
            }
        }
        if(check==0) {
            seatSelecteds.add(seat.seatName)
            showSeatSelected()
            calTotal = total()
            val total = findViewById<TextView>(R.id.totalSeat)
            total.text = calTotal.toString()
        }

    }

    fun showSeatSelected() {
        val sb = StringBuilder()

        for (seatId in seatSelecteds) {
            sb.append(seatId).append(", ")
        }
        if (sb.isNotEmpty()) {
            sb.setLength(sb.length - 2)
        }

        val seatSelectedsTextView = findViewById<TextView>(R.id.seatSelected)
        seatSelectedsTextView.text = sb.toString()
    }

    fun total():Double{
        val amount:Int = seatSelecteds.size
        calTotal = 45000 *  amount.toDouble()

        val total = findViewById<TextView>(R.id.totalSeat)
        total.text = calTotal.toString()
        return calTotal
    }
}