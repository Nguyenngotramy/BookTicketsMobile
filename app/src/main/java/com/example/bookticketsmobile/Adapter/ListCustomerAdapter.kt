package com.example.bookticketsmobile.Adapter

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import com.example.bookticketsmobile.Model.khachHang
import com.example.bookticketsmobile.R

class ListCustomerAdapter(activity: Activity, private var list: List<khachHang>)
    : ArrayAdapter<khachHang>(activity, R.layout.list_customer_ad, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_customer_ad, parent, false)
        val customer = list[position]

        val id = rowView.findViewById<TextView>(R.id.txtIdl)
        val khName = rowView.findViewById<TextView>(R.id.txtNamekhl)
        val email = rowView.findViewById<TextView>(R.id.txtemaill)
        val sdt = rowView.findViewById<TextView>(R.id.txtphonel)
        val dateOfbirth = rowView.findViewById<TextView>(R.id.txtbirdayl)
        val gender = rowView.findViewById<TextView>(R.id.txtGenderl)
       id.text = customer.idKhachHang.toString()
        khName.text = customer.hoVaTen
        email.text = customer.email
        sdt.text = customer.soDienThoai
        dateOfbirth.text = customer.ngaySinh
        gender.text = customer.gioiTinh

        return rowView
    }
    override fun getCount(): Int {
        return list.size
    }

    fun refreshData(newList: List<khachHang>) {
        list = newList
        notifyDataSetChanged()
    }

}
