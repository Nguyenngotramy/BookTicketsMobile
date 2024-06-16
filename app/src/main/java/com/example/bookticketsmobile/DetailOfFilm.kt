package com.example.bookticketsmobile

import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookticketsmobile.databinding.ActivityDetailOfFilmBinding


class DetailOfFilm : AppCompatActivity() {
    private lateinit var binding: ActivityDetailOfFilmBinding
    private var id: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDetailOfFilmBinding.inflate(layoutInflater)
        setContentView(binding.root)
        display()
        val webView = binding.webView
        val videoFrame: String =
            "<iframe width=\"100%\" height=\"100%\" src=\"https://www.youtube.com/embed/d1ZHdosjNX8?si=RHI2nbz3prmIYfLt\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" referrerpolicy=\"strict-origin-when-cross-origin\" allowfullscreen></iframe>"
        webView.loadData(videoFrame, "text/html", "utf-8")
        webView.settings.javaScriptEnabled = true
        webView.webChromeClient = WebChromeClient()

        binding.btnBook.setOnClickListener {
            val i1 = Intent(this, SelectDayTime::class.java)
            startActivity(i1)
        }
    }

    private fun display() {
        val intent = intent
        val bundle = intent.extras
        id = bundle?.getInt("idF", 0)
        val nameMovie = bundle?.getString("nameMovie")
        val category = bundle?.getString("category")
        val thoiLuong = bundle?.getLong("thoiLuong")

        if (thoiLuong != null) {
            val durationInMinutes: Long = thoiLuong
            val formattedTime: String = durationInMinutes.toFormattedTime()
            binding.txtThoiLuong.text = formattedTime
        }

        val dateOut = bundle?.getString("DateOut")
        val mota = bundle?.getString("Decri")
        val imgUriString = bundle?.getString("imgUri")

        binding.txtDateout.text = dateOut
        binding.detailName.text = nameMovie
        binding.catogory.text = category
        binding.txtMota.text = mota


        imgUriString?.let {
            val imgUri = Uri.parse(it)
            Glide.with(this)
                .load(imgUri)
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
                .error(R.drawable.baseline_image_24) // Image to show if loading fails
                .into(binding.detailImg1)
            Glide.with(this)
                .load(imgUri)
                .placeholder(R.drawable.placeholder_image) // Placeholder image while loading
                .error(R.drawable.baseline_image_24) // Image to show if loading fails
                .into(binding.detailImg2)
        }
    }

    fun Long.toFormattedTime(): String {
        val hours = this / 60
        val minutes = this % 60
        return "${hours}h ${minutes}m"
    }

    fun TransmitData() {

    }
}