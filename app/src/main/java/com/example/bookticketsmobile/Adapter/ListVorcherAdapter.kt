package com.example.bookticketsmobile.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bookticketsmobile.Model.khuyenMai
import com.example.bookticketsmobile.R

class ListVorcherAdapter (activity: Activity, private var list: List<khuyenMai>)
    : ArrayAdapter<khuyenMai>(activity, R.layout.list_vorcher_ad, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_vorcher_ad, parent, false)
        val vorcher = list[position]

        val idV = rowView.findViewById<TextView>(R.id.txtIdVorcher)
        val idVStr = rowView.findViewById<TextView>(R.id.txtnameVl)
        val percent = rowView.findViewById<TextView>(R.id.txtphantraml)
        val nd = rowView.findViewById<TextView>(R.id.txtndl)
        if (idV != null) {
            val i:Int = position
            idV.text = i.toString()
        }
        idVStr.text = vorcher.idKM
        percent.text = vorcher.phanTram.toString()
        nd.text = vorcher.noiDung

        return rowView
    }

    override fun getCount(): Int {
        return list.size
    }

    fun refreshData(newList: List<khuyenMai>) {
        list = newList
        notifyDataSetChanged()
    }

}
