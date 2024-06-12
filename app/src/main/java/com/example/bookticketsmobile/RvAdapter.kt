package com.example.bookticketsmobile
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class RvAdapter (var dayList:List<DayData>):RecyclerView.Adapter<RvAdapter.DayHolder>(){
    inner class DayHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DayHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.day_list_item, parent, false)
        return DayHolder(view)
    }

    override fun onBindViewHolder(holder: DayHolder, position: Int) {
        holder.itemView.apply {
            var day = findViewById<Button>(R.id.dayItem)
            day.text = "Ngày " + dayList[position].day
        }
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}