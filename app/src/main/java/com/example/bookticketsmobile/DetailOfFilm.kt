package com.example.bookticketsmobile

import android.content.Intent
import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import com.example.bookticketsmobile.databinding.ActivityDetailOfFilmBinding

private lateinit var binding:ActivityDetailOfFilmBinding
class DetailOfFilm : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailOfFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var webView = binding.webView
        var videoFrame: String =
            "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/d1ZHdosjNX8?si=RHI2nbz3prmIYfLt\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        webView.loadData(videoFrame, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

        binding.btnBook.setOnClickListener{
            val i1 = Intent(this, SelectDayTime::class.java)
            startActivity(i1)
        }
    }
}