package com.example.bookticketsmobile

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.core.content.ContextCompat

class CustomGridViewSeat (val activity: Activity, val list:List<SeatData>, var listener:OnSeatClickListener): ArrayAdapter<SeatData>(activity, R.layout.seat_list_item) {
    override fun getCount(): Int {
        return list.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contexts = activity.layoutInflater
        val rowView = contexts.inflate(R.layout.seat_list_item, parent, false)

        val seat = rowView.findViewById<Button>(R.id.seatBtn)
        seat.id = list[position].seatId
        seat.backgroundTintList = ContextCompat.getColorStateList(context, android.R.color.darker_gray)
        var seatSelected = ArrayList<Int>(5)

        seat.setOnClickListener {
            val currentColor = seat.backgroundTintList
            val defaultColor = currentColor?.defaultColor

            if(defaultColor == ContextCompat.getColor(context, android.R.color.darker_gray)) {
                seat.backgroundTintList = ContextCompat.getColorStateList(context, android.R.color.holo_orange_light)
                seatSelected.add(seat.id)
            } else {
                seat.backgroundTintList = ContextCompat.getColorStateList(context, android.R.color.darker_gray)
            }
            listener.onSeatClick(list[position])
        }
        return rowView
    }
}