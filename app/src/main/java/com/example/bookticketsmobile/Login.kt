package com.example.bookticketsmobile

import android.content.Intent
import android.graphics.Paint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.Observer
import com.example.bookticketsmobile.AdminUi.NavigationDrawerAdmin
import com.example.bookticketsmobile.Database.BookTicketsDatabase
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.databinding.ActivityLoginBinding
import com.example.bookticketsmobile.viewModel.bookTicketViewModel
import com.example.bookticketsmobile.viewModel.bookTicketViewModelFactory

class Login : AppCompatActivity() {
    private lateinit var btViewModel: bookTicketViewModel
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewModel()

        binding.btnRegister.setOnClickListener {
            val i = Intent(this, Register::class.java)
            startActivity(i)
        }

        binding.btnLogin.setOnClickListener {
            login()
        }

        binding.txtForgetPass.paintFlags = binding.txtForgetPass.paintFlags or Paint.UNDERLINE_TEXT_FLAG
    }

    private fun login() {
        val email = binding.txtEmailLogin.text.toString()
        val pass = binding.txtPassword.text.toString()

        if (email.isEmpty() || pass.isEmpty()) {
            Toast.makeText(this, "Please enter email and password", Toast.LENGTH_SHORT).show()
            return
        }

        if (email == "admin123@gmail.com" && pass == "admin123") {
            val i1 = Intent(this, NavigationDrawerAdmin::class.java)
            startActivity(i1)
        } else {
            btViewModel.getAllCustomers().observe(this, Observer { khList ->
                val khData = khList.map { Pair(it.email, it.matKhau) }
                val emails = khData.map { it.first }
                val passw = khData.map { it.second }

                if (emails.contains(email) && passw.contains(pass)) {
                    Toast.makeText(this, "Login successful", Toast.LENGTH_SHORT).show()

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
}
