package com.example.bookticketsmobile

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button

class CustomGridView (val activity: Activity, val timeList:List<TimeData>): ArrayAdapter<TimeData>(activity, R.layout.time_list_item){
    override fun getCount(): Int {
        return timeList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val context = activity.layoutInflater
        val rowView = context.inflate(R.layout.time_list_item, parent, false)

        val time = rowView.findViewById<Button>(R.id.timeItem)
        time.text = timeList[position].time
        return rowView
    }
}