package com.example.bookticketsmobile.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bookticketsmobile.Model.suatChieuDetail
import com.example.bookticketsmobile.R

class ListPerformanceAdapter(activity: Activity, private var list: List<suatChieuDetail>)
    : ArrayAdapter<suatChieuDetail>(activity, R.layout.list_performance_ad, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_performance_ad, parent, false)
        val p = list[position]

        val id = rowView.findViewById<TextView>(R.id.txtidP)
        val showDate = rowView.findViewById<TextView>(R.id.txtshowdate)
        val showTime = rowView.findViewById<TextView>(R.id.txtshowtime)
        val numSeat = rowView.findViewById<TextView>(R.id.txtNumSeat)
        val cinemaRoom = rowView.findViewById<TextView>(R.id.txtcinameRoomp)
        val movie = rowView.findViewById<TextView>(R.id.txtMoviep)
        val cinemaCluster = rowView.findViewById<TextView>(R.id.txtCinameClustersNamep)

        id?.text = p.idSuatChieu.toString()
        showDate?.text = p.ngayChieu
        showTime?.text = p.thoiGianChieu
        numSeat?.text = p.soLuongChoNgoi.toString()
        cinemaRoom?.text = p.phongChieu
        movie?.text = p.tenBoPhim
        cinemaCluster?.text = p.tenCumRap

        return rowView
    }

    override fun getCount(): Int {
        return list.size
    }

    fun refreshData(newList: List<suatChieuDetail>) {
        list = newList
        notifyDataSetChanged()
    }
}
