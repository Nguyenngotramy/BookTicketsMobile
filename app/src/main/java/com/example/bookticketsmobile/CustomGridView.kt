package com.example.bookticketsmobile

import android.app.Activity
import android.content.Intent
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button

class CustomGridView (val activity: Activity, val timeList:List<TimeData>): ArrayAdapter<TimeData>(activity, R.layout.time_list_item){
    override fun getCount(): Int {
        return timeList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val cont = activity.layoutInflater
        val rowView = cont.inflate(R.layout.time_list_item, parent, false)

        val time = rowView.findViewById<Button>(R.id.timeItem)
        time.text = timeList[position].time

        time.setOnClickListener {
            val i1 = Intent(context,SelectSeat::class.java)
            context.startActivity(i1)
        }
        return rowView
    }
}