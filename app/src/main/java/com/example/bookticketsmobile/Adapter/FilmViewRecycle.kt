package com.example.bookticketsmobile.Adapter

import android.content.Context
import android.content.Intent
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookticketsmobile.AdminUi.UpdateFoodActivity
import com.example.bookticketsmobile.DetailOfFilm
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.FilmlistItemBinding
import com.example.bookticketsmobile.databinding.ListMoviesAdBinding
import java.io.File
import java.io.FileOutputStream
import java.io.IOException

class FilmViewRecycle(private val context: Context) : RecyclerView.Adapter<FilmViewRecycle.FilmViewHolder>() {
    inner class FilmViewHolder(val binding: FilmlistItemBinding) :
        RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<Phim>() {
        override fun areItemsTheSame(oldItem: Phim, newItem: Phim): Boolean {
            return oldItem.idPhim == newItem.idPhim
        }

        override fun areContentsTheSame(oldItem: Phim, newItem: Phim): Boolean {
            return oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this, differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return FilmViewHolder(
            FilmlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val currentFilm = differ.currentList[position]
        holder.binding.apply {
            val id: Int = currentFilm.idPhim
            val idStr: String = id.toString()
            /* idFilml.text = idStr*/
            nameFilm.text = currentFilm.tenBoPhim
            currentFilm.HinhAnh?.let {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                imageFilm.setImageBitmap(bitmap)
            } ?: run {
                imageFilm.setImageResource(R.drawable.placeholder_image) // Thay thế bằng hình ảnh placeholder nếu BLOB là null
            }
            bookBtn.setOnClickListener {
                val intent = Intent(context, DetailOfFilm::class.java)
                val bundle = Bundle().apply {
                    putInt("idF", currentFilm.idPhim)
                    putString("nameMovie", currentFilm.tenBoPhim)
                    putString("category", currentFilm.theLoai)
                    putString("Decri", currentFilm.moTa)
                    currentFilm.thoiLuong?.let { putLong("thoiLuong", it) }
                    putString("DateOut", currentFilm.thoiGianRaRap)
                    currentFilm.HinhAnh?.let {
                        val uri = saveByteArrayToFileAndGetUri(holder.itemView.context, it)
                        putString("imgUri", uri.toString())
                    }
                }
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }
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

}
