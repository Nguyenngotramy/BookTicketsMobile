package com.example.bookticketsmobile

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.bookticketsmobile.databinding.ActivityMainBinding


private lateinit var binding: ActivityMainBinding
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLoginHome.setOnClickListener{
            val i1 = Intent(this,DetailOfFilm::class.java)
            startActivity(i1)
        }
        binding.btnRegisterHome.setOnClickListener{
            val i2 = Intent(this, Register::class.java)
            startActivity(i2)
        }
    }
}