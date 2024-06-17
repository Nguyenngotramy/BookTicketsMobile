package com.example.bookticketsmobile

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class Payment : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_payment)

        val i = intent
        val idFilm = i.getIntExtra("idFilm", 0)
        val imageBytes = i.getByteArrayExtra("image_bytes")
        val nameFilm = i.getStringExtra("nameMovieS")?: "Unknown Film"
        val time = i.getStringExtra("time")?: "Unknown Time"
        val selectedSeat = i.getStringArrayExtra("selectedSeat")?: arrayOf("")
        val comboNames = i.getStringArrayExtra("comboNames") ?: arrayOf("")
        val comboAmounts = i.getIntArrayExtra("comboAmounts")
        val thoiLuong = i.getLongExtra("thoiLuong", 0)
        val totalSeat = i.getDoubleExtra("totalSeat", 0.0)
        val totalCombo = i.getDoubleExtra("totalCombo", 0.0)

        if (imageBytes != null) {
            val bitmap = BitmapFactory.decodeByteArray(imageBytes, 0, imageBytes.size)
//            binding.imgFilmD.setImageBitmap(bitmap)
//            binding.detailImg1.setImageBitmap(bitmap)
            val imgOfFilm = findViewById<ImageView>(R.id.imgOfFilm)
            imgOfFilm.setImageBitmap(bitmap)
        }

        val comboSelecteds = if (comboNames.isNotEmpty() && comboAmounts != null) {
            comboNames.toList().zip(comboAmounts.toList()).toMutableList()
        } else {
            mutableListOf<Pair<String, Int>>()
        }


        val imgOfFilm = findViewById<ImageView>(R.id.imgOfFilm)
        val nameOfFilm = findViewById<TextView>(R.id.nameOfFilm)
        val timeOfTicket = findViewById<TextView>(R.id.timeOfTicket)
        val listSeat = findViewById<TextView>(R.id.listSeat)
        val listCombo = findViewById<TextView>(R.id.listCombo)
        val txtThoiLuong = findViewById<TextView>(R.id.txtThoiLuongPay)

//        imgOfFilm.setImageResource(imgFilm)
        nameOfFilm.text = nameFilm
        timeOfTicket.text = time
        txtThoiLuong.text = "Thời lượng: $thoiLuong"

        //show list chỗ ngồi đã chọn
        val sb1 = StringBuilder()
        for (seatName in selectedSeat) {
            sb1.append(seatName).append(", ")
        }
        if (sb1.isNotEmpty()) {
            sb1.setLength(sb1.length - 2)
        }
        listSeat.text = sb1.toString()

        //show list combo đã chọn
        val sb2 = StringBuilder()
        for ((name, amount) in comboSelecteds) {
            sb2.append("$name * $amount\n")
        }
        listCombo.text = sb2.toString()

        //show tiền tạm tính
        val tempTotalDouble = totalSeat + totalCombo
        val tempTotal = findViewById<TextView>(R.id.tempTotal)
        tempTotal.text = tempTotalDouble.toString()

        val total = findViewById<TextView>(R.id.mainTotal)
        total.text = tempTotalDouble.toString()

        // Thanh toán
        val payBtn = findViewById<Button>(R.id.payBtn)
        payBtn.setOnClickListener {
            val i1 = Intent(this, PaymentSuccess::class.java)
            startActivity(i1)
        }
    }
}