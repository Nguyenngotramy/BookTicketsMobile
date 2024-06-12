package com.example.bookticketsmobile.AdminUi

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FragmentAddMoviesBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date


private var _binding: FragmentAddMoviesBinding? = null
private val binding get() = _binding!!
private var datePickerDialog: DatePickerDialog? = null
private var timePickerDialog: TimePickerDialog? = null
private var selectedImageUri: Uri? = null
private lateinit var btViewModel: bookTicketViewModel
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
        binding.btnimg.setOnClickListener {
            openImagePicker()
        }
        setupViewModel()
        binding.btnAddd.setOnClickListener {
            addMovies()
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

    private fun openImagePicker() {
        val intent = Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
        intent.type = "image/*"

        if (intent.resolveActivity(requireContext().packageManager) != null) {
            startActivityForResult(intent, PICK_IMAGE_REQUEST)
        } else {
            Toast.makeText(requireContext(), "Không tìm thấy ứng dụng xử lý ảnh.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == Activity.RESULT_OK && data != null) {
            selectedImageUri = data.data

            try {
                val inputStream = requireContext().contentResolver.openInputStream(selectedImageUri!!)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream?.close()

                binding.img.setImageBitmap(bitmap)
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Lỗi khi đọc ảnh từ Gallery.", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun saveBitmapToDrawableFolder(context: Context, filename: String, bitmap: Bitmap) {
        try {
            val outputStream = context.openFileOutput(filename, Context.MODE_PRIVATE)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream)
            outputStream.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun setupViewModel() {
        val btReposition = BookTicketsRepository(BookTicketsDatabase(requireContext()))
        val viewModelProviderFactory = bookTicketViewModelFactory(requireActivity().application,btReposition)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }
    private fun addMovies(){
        val nameMovie = binding.txtNameMovie.text.toString().trim()
        val category = binding.txtCategory.text.toString().trim()
        val describe = binding.txtDescribe.text.toString().trim()
        val timeString = binding.timePickerButton.text.toString().trim()
        val format = SimpleDateFormat("HH:mm")
        val date: Date = format.parse(timeString)
        val durian: Long = date.time
        val dateOut = binding.datePickerButton.text.toString().trim()
        if (selectedImageUri != null) {
            try {


                val bitmap = (binding.img.drawable as BitmapDrawable).bitmap
                val byteArray = ByteArrayOutputStream().apply {
                    bitmap.compress(Bitmap.CompressFormat.PNG, 100, this)
                }.toByteArray()
                saveBitmapToDrawableFolder(requireContext(), "my_image.png", bitmap)

                if(nameMovie.isNotEmpty()&&category.isNotEmpty() && describe.isNotEmpty() && timeString.isNotEmpty() && dateOut.isNotEmpty()) {
                    val mv = Phim(0, nameMovie, category, describe, durian, dateOut, byteArray)
                    btViewModel.addMovies(mv)
                    Toast.makeText(requireContext(), "Add success", Toast.LENGTH_SHORT).show()
                    clearTxt()
                }else{
                    Toast.makeText(requireContext(), "Fill out form", Toast.LENGTH_SHORT).show()

                }
            } catch (e: IOException) {
                e.printStackTrace()
                Toast.makeText(requireContext(), "Lỗi khi đọc ảnh từ Gallery.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(requireContext(), "Vui lòng chọn hình ảnh.", Toast.LENGTH_SHORT).show()
        }
    }
    private fun clearTxt(){
        binding.txtNameMovie.setText("")
        binding.txtCategory.setText("")
        binding.txtDescribe.setText("")
        binding.timePickerButton.setText("Movies durian")
        binding.datePickerButton.setText("Time to hit theaters")
        binding.img.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.baseline_image_24));
    }
    companion object {
        const val PICK_IMAGE_REQUEST = 1
    }
}