package com.example.bookticketsmobile

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PopcornCombo : AppCompatActivity(), OnComboClickListener {
    var comboSelecteds = mutableListOf<Pair<String, Int>>()
    var comboList = mutableListOf<ComboData>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_popcorn_combo)

        // lấy dữ liệu từ SelectDayTime
        val i = intent
        val idFilm = i.getIntExtra("idFilm", 0)
        val imgFilm = i.getIntExtra("imageFilm", 0)
        val nameFilm = i.getStringExtra("nameFilm")?: "Unknown Film"
        val time = i.getStringExtra("time")?: "Unknown Time"
        val selectedSeat = i.getStringArrayExtra("selectedSeat") ?: emptyArray()
        val totalSeat = i.getDoubleExtra("totalSeat", 0.0)

        comboList.add(ComboData(1, "Bắp nước haha", 55000.0, R.drawable.latmat7poster))
        comboList.add(ComboData(2, "Bắp nước hyhy", 55000.0, R.drawable.doremonposter))
        comboList.add(ComboData(3, "Bắp nước hoho", 55000.0, R.drawable.thanhxuanposter))
        comboList.add(ComboData(4, "Bắp nước hehe", 55000.0, R.drawable.doremonposter))
        comboList.add(ComboData(5, "Bắp nước huhu", 55000.0, R.drawable.hanhtinhkhiposter))

        val customAdapter = CustomListViewCombo(this, comboList, this)
        val list = findViewById<ListView>(R.id.comboList)
        list.adapter = customAdapter

        val nextBtn = findViewById<Button>(R.id.nextBtn2)
        nextBtn.setOnClickListener {
            val i1 = Intent(this, Payment::class.java)
            i1.putExtra("idFilm", idFilm)
            i1.putExtra("imageFilm", imgFilm)
            i1.putExtra("nameFilm", nameFilm)
            i1.putExtra("time", time)
            i1.putExtra("selectedSeat", selectedSeat)
            val comboName = comboSelecteds.map { it.first }.toTypedArray()
            val comboAmounts = comboSelecteds.map { it.second }.toIntArray()
            i1.putExtra("comboNames", comboName)
            i1.putExtra("comboAmounts", comboAmounts)
            i1.putExtra("totalSeat", totalSeat)
            val calTotalCombo:Double = totalCombo()
            i1.putExtra("totalCombo", calTotalCombo)
            startActivity(i1)

        }

//        totalCombo()
    }

    override fun onClickPlusButton(combo: ComboData) {
        var check:Int = 0
        for (i in comboSelecteds.indices) {
            val comboName = comboSelecteds[i].first
            var amountCb = comboSelecteds[i].second
            if (combo.nameCombo == comboName) {
                if (amountCb > 0) {
                    amountCb++
                    comboSelecteds[i] = Pair(comboName, amountCb)
                    val calTotalCombo:Double = totalCombo()
                    val totalCombo = findViewById<TextView>(R.id.totalCombo)
                    totalCombo.text = calTotalCombo.toString()
                    check=1
                    break
                }
            }

        }
        if(check==0) {
            comboSelecteds.add(Pair(combo.nameCombo, 1))
            val calTotalCombo:Double = totalCombo()
            val totalCombo = findViewById<TextView>(R.id.totalCombo)
            totalCombo.text = calTotalCombo.toString()

        }
    }

    override fun onCLickMinusButton(combo: ComboData) {
        for (i in comboSelecteds.indices) {
            val comboName = comboSelecteds[i].first
            var amountCb = comboSelecteds[i].second
            if (combo.nameCombo == comboName) {
                if (amountCb > 0) {
                    amountCb--
                    if(amountCb==0) {
                        comboSelecteds.removeAt(i)
                    } else {
                        comboSelecteds[i] = Pair(comboName, amountCb)
                    }
                    val calTotalCombo:Double = totalCombo()
                    val totalCombo = findViewById<TextView>(R.id.totalCombo)
                    totalCombo.text = calTotalCombo.toString()
                    break
                }
            }
        }
    }


    fun totalCombo():Double{
        var calTotalCombo:Double = 0.0
        for(comboItem in comboSelecteds) {
            val comboName = comboItem.first
            val amountCb:Int = comboItem.second
            for(combo in comboList){
                if(comboName == combo.nameCombo) {
                    calTotalCombo += combo.priceCombo * amountCb
                    val totalCombo = findViewById<TextView>(R.id.totalCombo)
                    totalCombo.text = calTotalCombo.toString()
                }
            }
        }

        return calTotalCombo

    }

}