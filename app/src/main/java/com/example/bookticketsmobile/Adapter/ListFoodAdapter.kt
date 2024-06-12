package com.example.bookticketsmobile.Adapter

import android.app.Activity
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.bookticketsmobile.Model.cbDoAn
import com.example.bookticketsmobile.R

class ListFoodAdapter (activity: Activity, private var list: List<cbDoAn>)
    : ArrayAdapter<cbDoAn>(activity, R.layout.list_food_ad, list) {

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val rowView = convertView ?: LayoutInflater.from(context).inflate(R.layout.list_food_ad, parent, false)
        val food = list[position]

        val id = rowView.findViewById<TextView>(R.id.txtIdFl)
        val nameFood = rowView.findViewById<TextView>(R.id.txtnameFl)
        val price = rowView.findViewById<TextView>(R.id.txtpricel)
        val imgF = rowView.findViewById<ImageView>(R.id.imgF)

        id.text = food.idDoAn.toString()
        nameFood.text = food.tenDoAn
        price.text = food.gia.toString()

        val bitmap = food.HinhAnh?.let { BitmapFactory.decodeByteArray(food.HinhAnh, 0, it.size) }
        imgF.setImageBitmap(bitmap)

        return rowView
    }

    override fun getCount(): Int {
        return list.size
    }

    fun refreshData(newList: List<cbDoAn>) {
        list = newList
        notifyDataSetChanged()
    }

}
