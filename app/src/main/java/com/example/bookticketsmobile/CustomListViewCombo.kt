package com.example.bookticketsmobile

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView

class CustomListViewCombo (val activity: Activity, val comboList: List<ComboData>, var listener: OnComboClickListener): ArrayAdapter<ComboData>(activity, R.layout.combo_list_item) {
    override fun getCount(): Int {
        return comboList.size
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val contexts = activity.layoutInflater
        val rowView = contexts.inflate(R.layout.combo_list_item, parent, false)

        val combo = rowView.findViewById<CardView>(R.id.comboFood)
        val imgCombo = rowView.findViewById<ImageView>(R.id.imgCombo)
        val nameCombo = rowView.findViewById<TextView>(R.id.nameCombo)
        val priceCombo = rowView.findViewById<TextView>(R.id.priceCombo)

        imgCombo.setImageResource(comboList[position].imgCombo)
        nameCombo.text = comboList[position].nameCombo
        priceCombo.text = comboList[position].priceCombo.toString()
        combo.id = comboList[position].idCombo

        val minusButton = rowView.findViewById<Button>(R.id.minusBtn)
        val plusButton = rowView.findViewById<Button>(R.id.plusBtn)

        val amount = rowView.findViewById<TextView>(R.id.amount)
        var amountInt = amount.text.toString().toInt()
        minusButton.setOnClickListener {
            if(amountInt==0) {
                amount.text = "0"
            } else {
                amountInt--
                amount.text = amountInt.toString()
            }
            listener.onCLickMinusButton(comboList[position])
        }
        plusButton.setOnClickListener {
            amountInt++
            amount.text = amountInt.toString()
            listener.onClickPlusButton(comboList[position])
        }
        return rowView
    }

    fun showAmount() {

    }
}