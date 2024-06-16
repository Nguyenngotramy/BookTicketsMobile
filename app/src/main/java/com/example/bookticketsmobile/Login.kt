package com.example.bookticketsmobile

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Paint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.AdminUi.NavigationDrawerAdmin
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.databinding.ActivityLoginBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory

class Login : AppCompatActivity() {
    private lateinit var btViewModel: bookTicketViewModel
    private lateinit var binding: ActivityLoginBinding
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Khởi tạo SharedPreferences
        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

        binding.btnRegister.setOnClickListener {
            val i = Intent(this, Register::class.java)
            startActivity(i)
        }

        setupViewModel()

        binding.btnLogin.setOnClickListener {
//            login()
            val i1 = Intent(this, Home::class.java)
            startActivity(i1)
        }

        binding.txtForgetPass.paintFlags = binding.txtForgetPass.paintFlags or Paint.UNDERLINE_TEXT_FLAG
        checkLoginStatus()
    }

    private fun login() {
        val email = binding.txtEmailLogin.text.toString()
        val pass = binding.txtPassword.text.toString()

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }
        if (email == "admin123@gmail.com" && pass == "admin123") {
            saveLoginStatus(email)
            val i1 = Intent(this, NavigationDrawerAdmin::class.java)
            startActivity(i1)
            finish()
        } else {
            btViewModel.getAllCustomers().observe(this, Observer { khList ->
                val khData = khList.map { Pair(it.email, it.matKhau) }
                val emails = khData.map { it.first }
                val passw = khData.map { it.second }

                if (emails.contains(email) && passw.contains(pass)) {
                    saveLoginStatus(email)
                    val i2 = Intent(this, Home::class.java)
                    startActivity(i2)
                    finish()
                    val customer = khList.find { it.email == email }
                    val name = customer?.hoVaTen
                    val intent = Intent(this, AccountFragment::class.java)
                    val bundle = Bundle()
                    bundle.putString("email", email)
                    bundle.putString("name", name)
                    intent.putExtras(bundle)
                   this.startActivity(intent)
                } else {
                    Toast.makeText(this, "Invalid email or password", Toast.LENGTH_SHORT).show()
                }
            })
        }
    }

    private fun setupViewModel() {
        val btRepository = BookTicketsRepository(BookTicketsDatabase(this))
        val viewModelProviderFactory = bookTicketViewModelFactory(application, btRepository)
        btViewModel = ViewModelProvider(this, viewModelProviderFactory)[bookTicketViewModel::class.java]
    }

    private fun saveLoginStatus(email: String) {
        val editor = sharedPreferences.edit()
        editor.putString("email", email)
        editor.apply()
    }
    private fun checkLoginStatus() {
        val isLoggedIn = sharedPreferences.contains("email")
        if (isLoggedIn) {
            val email = sharedPreferences.getString("email", "")
            val i = Intent(this, Home::class.java)
            startActivity(i)
            finish()
        }
    }
}
