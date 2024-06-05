package com.example.bookticketsmobile.AdminUi

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.bookticketsmobile.R
import java.util.Calendar
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import com.example.bookticketsmobile.databinding.FragmentAddMoviesBinding

private var _binding: FragmentAddMoviesBinding? = null
private val binding get() = _binding!!
private var datePickerDialog: DatePickerDialog? = null
private var timePickerDialog: TimePickerDialog? = null
class AddMoviesFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentAddMoviesBinding.inflate(inflater, container, false)
        initDatePicker()
        binding.datePickerButton.setOnClickListener {
            openDatePicker()
        }
        initTimePicker()
        binding.timePickerButton.setOnClickListener {
            openTimePicker()
        }
        return binding.root
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
        datePickerDialog = DatePickerDialog(requireContext(), style, dateSetListener, year, month, day)
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

    fun openDatePicker() {
        datePickerDialog?.show()
    }

    private fun initTimePicker() {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hourOfDay, minute ->
            val time = makeTimeString(hourOfDay, minute)
            binding.timePickerButton.text = time
        }
        val cal: Calendar = Calendar.getInstance()
        val hour: Int = cal.get(Calendar.HOUR_OF_DAY)
        val minute: Int = cal.get(Calendar.MINUTE)
        val style: Int = AlertDialog.THEME_HOLO_LIGHT
         timePickerDialog = TimePickerDialog(requireContext(), style, timeSetListener, hour, minute, true)
    }

    private fun makeTimeString(hourOfDay: Int, minute: Int): String {
        return String.format("%02d:%02d", hourOfDay, minute)
    }
    fun openTimePicker() {
        timePickerDialog?.show()
    }

}