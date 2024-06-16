package com.example.bookticketsmobile

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button

class CustomGridView (val activity: Activity, val timeList:List<TimeData>, val idFilm:Int, val imgFilm:Int, val nameFilm:String): ArrayAdapter<TimeData>(activity, R.layout.time_list_item){
    override fun getCount(): Int {
        return timeList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val cont = activity.layoutInflater
        val rowView = cont.inflate(R.layout.time_list_item, parent,false)

        val time = rowView.findViewById<Button>(R.id.timeItem)
        time.text = timeList[position].time

        time.setOnClickListener {
            val i1 = Intent(context,SelectSeat::class.java)
            i1.putExtra("idFilm", idFilm)
            i1.putExtra("imageFilm", imgFilm)
            i1.putExtra("nameFilm", nameFilm)
            i1.putExtra("time", time.text)
            context.startActivity(i1)
        }
        return rowView
    }
}