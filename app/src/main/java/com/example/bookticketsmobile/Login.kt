package com.example.bookticketsmobile

import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bookticketsmobile.databinding.ActivityLoginBinding


private lateinit var bingding : ActivityLoginBinding

class Login : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        bingding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(bingding.root)
        bingding.btnRegister.setOnClickListener {
            val i = Intent(this,Register::class.java)
            startActivity(i)
        }
        bingding.btnLogin.setOnClickListener {
            val i1 = Intent(this,Home::class.java)
            startActivity(i1)
        }
        bingding.txtForgetPass.paintFlags = bingding.txtForgetPass.paintFlags or Paint.UNDERLINE_TEXT_FLAG

    }
}