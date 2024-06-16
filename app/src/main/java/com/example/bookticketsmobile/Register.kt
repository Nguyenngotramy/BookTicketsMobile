package com.example.bookticketsmobile
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.Model.khachHang
import com.example.bookticketsmobile.databinding.ActivityRegisterBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory
import java.util.Calendar

class Register : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private var datePickerDialog: DatePickerDialog? = null
    private lateinit var btViewModel: bookTicketViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initDatePicker()
        // Khởi tạo repository
      /*  repository = BookTicketsRepository(BookTicketsDatabase.invoke(this))*/
        setupViewModel()
        binding.btnRegisterkh.setOnClickListener {
            registerKH()
            val i = Intent(this, Login::class.java)
            startActivity(i)
            finish()
        }
        // Sử dụng lifecycleScope để thực hiện các hoạt động cơ sở dữ liệu
   /*     lifecycleScope.launch {*/
           /* val kh = khachHang(3, "Nn", "abc@gmail.com", "29348", "2764387", "20/07/2004", "male")
            repository.register(kh)*/
    /*    }*/

}


    private fun setupViewModel() {
        val btReposition = BookTicketsRepository(BookTicketsDatabase(this))
        val viewModelProviderFactory = bookTicketViewModelFactory(application,btReposition)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
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
                binding.btnGender.text = selectedGender
                dialog.dismiss()
            }

        val dialog = builder.create()
        dialog.show()
    }

    fun opengender(view: View?) {
        showGenderDialog()
    }


    private fun registerKH(){
        val fullName = binding.txtFullName.text.toString().trim()
        val phone = binding.txtPhone.text.toString().trim()
        val email = binding.txtEmail.text.toString().trim()
        val password = binding.txtPassword.text.toString().trim()
        val dateOfBirth = binding.datePickerButton.text.toString().trim()
        val gender = binding.btnGender.text.toString().trim()
        if (fullName.isNotEmpty() && phone.isNotEmpty() && email.isNotEmpty() && password.isNotEmpty() && dateOfBirth.isNotEmpty() && gender.isNotEmpty()) {
            val kh = khachHang(0, fullName, email,phone, password, dateOfBirth, gender)

            btViewModel.register(kh)
            Toast.makeText(this,"Register success",Toast.LENGTH_SHORT).show()
            binding.txtFullName.setText("")
            binding.txtPhone.setText("")
            binding.txtPassword.setText("")
            binding.txtEmail.setText("")
            binding.datePickerButton.setText("Date of birth")
            binding.btnGender.setText("Gender")

        }else{
            Toast.makeText(this, "Please fill out the form!", Toast.LENGTH_SHORT).show()
        }



    }


}
