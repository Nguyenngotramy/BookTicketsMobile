package com.example.bookticketsmobile

import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.net.Uri
import android.os.Bundle
import android.webkit.WebChromeClient
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.bookticketsmobile.databinding.ActivityDetailOfFilmBinding
import java.io.ByteArrayOutputStream
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

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
            TransmitData(this)
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
                .placeholder(R.drawable.placeholder_image)
                .error(R.drawable.baseline_image_24)
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

    private fun TransmitData(context: Context) {
        val intent = Intent(context, SelectDayTime::class.java)
        val bundle = Bundle().apply {
            id?.let { putInt("idF", it) }
            val name: String = binding.detailName.text.toString()
            val category: String = binding.catogory.text.toString()
            val mota: String = binding.txtMota.text.toString()
            val date: String = binding.txtDateout.text.toString()
            putString("nameMovieS", name)
            putString("categoryS", category)
            putString("DecriS", mota)

            val thoiLuongStr = binding.txtThoiLuong.text.toString()
            val totalMinutes = convertFormattedTimeToMinutes(thoiLuongStr)

            putLong("thoiLuongS", totalMinutes)

            putString("DateOutS", date)

            binding.detailImg1.let {
                val byteArray = getByteArrayFromImageView(it)
                val uri = saveByteArrayToFileAndGetUri(context, byteArray)
                putString("imgUriS", uri.toString())
            }

        }

        intent.putExtras(bundle)
        context.startActivity(intent)
    }

    private fun getByteArrayFromImageView(imageView: ImageView): ByteArray {
        val bitmap = (imageView.drawable as BitmapDrawable).bitmap
        val byteArrayOutputStream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream)
        return byteArrayOutputStream.toByteArray()
    }

    fun saveByteArrayToFileAndGetUri(context: Context, byteArray: ByteArray): Uri? {
        var fileOutputStream: FileOutputStream? = null
        var fileUri: Uri? = null
        try {
            val uniqueFileName = "temp_image_file_${System.currentTimeMillis()}.jpg"
            val file = File(context.filesDir, uniqueFileName)
            fileOutputStream = FileOutputStream(file)
            fileOutputStream.write(byteArray)
            fileUri = Uri.fromFile(file)
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            try {
                fileOutputStream?.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
        return fileUri
    }
    fun convertFormattedTimeToMinutes(formattedTime: String): Long {
        val parts = formattedTime.split("h", "m")
        val hours = parts[0].trim().toLong()
        val minutes = parts[1].trim().toLong()

        return hours * 60 + minutes
    }

}
