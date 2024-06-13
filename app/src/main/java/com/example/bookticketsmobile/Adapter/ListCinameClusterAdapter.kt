package com.example.bookticketsmobile.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bookticketsmobile.Model.cumRap
import com.example.bookticketsmobile.R

class ListCinameClusterAdapter  (activity: Activity, private var list: List<cumRap>)
    : ArrayAdapter<cumRap>(activity, R.layout.list_ciname_cluster_ad, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_ciname_cluster_ad, parent, false)
        val cc = list[position]

        val id = rowView.findViewById<TextView>(R.id.txtidP)
        val namePcity = rowView.findViewById<TextView>(R.id.txtshowdate)
        val namecc = rowView.findViewById<TextView>(R.id.txtshowtime)


        id.text = cc.idCumRap.toString()
        namePcity.text = cc.tinhTP
        namecc.text = cc.tenCumRap

        return rowView
    }

    override fun getCount(): Int {
        return list.size
    }

    fun refreshData(newList: List<cumRap>) {
        list = newList
        notifyDataSetChanged()
    }

}
