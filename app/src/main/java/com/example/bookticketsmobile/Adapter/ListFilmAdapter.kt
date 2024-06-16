package com.example.bookticketsmobile.Adapter

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.R
import com.example.bookticketsmobile.databinding.ListMoviesAdBinding

class ListFilmAdapter: RecyclerView.Adapter<ListFilmAdapter.FilmViewHolder>() {
  inner class FilmViewHolder(val binding:ListMoviesAdBinding):RecyclerView.ViewHolder(binding.root)
    private val differCallBack = object :DiffUtil.ItemCallback<Phim>(){
        override fun areItemsTheSame(oldItem: Phim, newItem: Phim): Boolean {
            return oldItem.idPhim == newItem.idPhim &&
                    oldItem.theLoai == newItem.theLoai &&
                    oldItem.moTa == newItem.moTa &&
                    oldItem.thoiLuong == newItem.thoiLuong &&
                    oldItem.thoiGianRaRap == newItem.thoiGianRaRap &&
                    oldItem.HinhAnh == newItem.HinhAnh
        }

        override fun areContentsTheSame(oldItem: Phim, newItem: Phim): Boolean {
           return  oldItem == newItem
        }
    }
    val differ = AsyncListDiffer(this,differCallBack)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        return  FilmViewHolder(
            ListMoviesAdBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        )
    }

    override fun getItemCount(): Int {
       return differ.currentList.size
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val currentFilm = differ.currentList[position]
        holder.binding.apply {
            val id:Int = currentFilm.idPhim
            val idStr:String = id.toString()
            idFilml.text = idStr
            txtNameMovieL.text = currentFilm.tenBoPhim
            txtCategoryL.text = currentFilm.theLoai
            txtDescribeL.text = currentFilm.moTa
            if (currentFilm.thoiLuong != null) {
                val durationInMinutes: Long = currentFilm.thoiLuong!!
                val formattedTime: String = durationInMinutes.toFormattedTime()
                txtTimeL.text = formattedTime
            }
            txtDateOutL.text = currentFilm.thoiGianRaRap

            currentFilm.HinhAnh?.let {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                val resizedBitmap = resizeBitmap(bitmap, 800, 800) // Resize to 800x800 or adjust as needed
                imgL.setImageBitmap(resizedBitmap)
            } ?: run {
                imgL.setImageResource(R.drawable.placeholder_image) // Placeholder image if BLOB is null
            }
        }
    }
    fun resizeBitmap(source: Bitmap, maxWidth: Int, maxHeight: Int): Bitmap {
        var width = source.width
        var height = source.height

        if (width > maxWidth) {
            val ratio = maxWidth.toFloat() / width
            width = maxWidth
            height = (height * ratio).toInt()
        }

        if (height > maxHeight) {
            val ratio = maxHeight.toFloat() / height
            height = maxHeight
            width = (width * ratio).toInt()
        }

        return Bitmap.createScaledBitmap(source, width, height, true)
    }



    fun Long.toFormattedTime(): String {
        val hours = this / 60
        val minutes = this % 60
        return "${hours}h ${minutes}m"
    }
}