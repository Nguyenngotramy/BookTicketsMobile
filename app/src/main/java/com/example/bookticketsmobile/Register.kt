package com.example.bookticketsmobile

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import com.example.newsprojectpractice.R
import com.example.newsprojectpractice.databinding.ActivityRegisterBinding


import java.util.Calendar

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var datePickerDialog: DatePickerDialog? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
       /* val list = mutableListOf<String>()
        list.add("Male")
        list.add("FeMale")

        //khai bao bo dieu huong
        val adt = ArrayAdapter(this,
            R.layout.simple_spinner_item,list
        )
        //goi spinner
        binding.spnGioiTinh.adapter = adt*/
        initDatePicker()
    }

    private fun getTodaysDate(): String? {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        var month: Int = cal.get(Calendar.MONTH)
        month = month + 1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        return makeDateString(day, month, year)
    }

    private fun initDatePicker() {
        val dateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date = makeDateString(day, month, year)
                binding.datePickerButton.setText(date)
            }
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        val month: Int = cal.get(Calendar.MONTH)
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        val style: Int = AlertDialog.THEME_HOLO_LIGHT
        datePickerDialog = DatePickerDialog(this, style, dateSetListener, year, month, day)
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String? {
        return getMonthFormat(month) + " " + day + " " + year
    }

    private fun getMonthFormat(month: Int): String {
        if (month == 1) return "JAN"
        if (month == 2) return "FEB"
        if (month == 3) return "MAR"
        if (month == 4) return "APR"
        if (month == 5) return "MAY"
        if (month == 6) return "JUN"
        if (month == 7) return "JUL"
        if (month == 8) return "AUG"
        if (month == 9) return "SEP"
        if (month == 10) return "OCT"
        if (month == 11) return "NOV"
        return if (month == 12) "DEC" else "JAN"
    }

    fun openDatePicker(view: View?) {
        datePickerDialog?.show()
    }
    private fun showGenderDialog() {
        val genderOptions = arrayOf("Male", "Female", "Other")

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Select Gender")
            .setItems(genderOptions) { dialog, which ->
                val selectedGender = genderOptions[which]
                // Xử lý khi người dùng chọn giới tính
                // Ví dụ:
                // when (selectedGender) {
                //     "Male" -> { /* Xử lý khi chọn giới tính Nam */ }
                //     "Female" -> { /* Xử lý khi chọn giới tính Nữ */ }
                //     "Other" -> { /* Xử lý khi chọn giới tính Khác */ }
                // }
                binding.btnGender.text = selectedGender
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }

    fun opengender(view: View?) {
        showGenderDialog()
    }



}
