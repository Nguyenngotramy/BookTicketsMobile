package com.example.bookticketsmobile.Adapter

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.SelectSeat
import com.example.bookticketsmobile.TimeData

class CustomGridView (val activity: Activity, var list:List<TimeData>, val idFilm:Int ,val nameFilm:String, val thoiLuong:Long):
    ArrayAdapter<TimeData>(activity, R.layout.time_list_item,list) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: LayoutInflater.from(context)
            .inflate(R.layout.time_list_item, parent, false)
        val time = list[position]

        val timebtn = rowView.findViewById<Button>(R.id.timeItem)
        timebtn.text = time.thoiGianChieu
        /* timebtn.setOnClickListener {
            val i1 = Intent(context, SelectSeat::class.java)
            i1.putExtra("idFilm", idFilm)
            i1.putExtra("imageFilm", imgFilm)
            i1.putExtra("nameFilm", nameFilm)
            i1.putExtra("time", time.text)
            context.startActivity(i1)
        }*/
        val i1 = Intent(context, SelectSeat::class.java)
        i1.putExtra("idFilm", idFilm)
//        i1.putExtra("imageFilm", imageBytes)
        i1.putExtra("nameFilm", nameFilm)
        i1.putExtra("time", timebtn.text)
        i1.putExtra("thoiLuong", thoiLuong)
        context.startActivity(i1)
        return rowView
    }
    fun refreshData(newList: List<TimeData>) {
        list = newList
        notifyDataSetChanged()
    }

}
