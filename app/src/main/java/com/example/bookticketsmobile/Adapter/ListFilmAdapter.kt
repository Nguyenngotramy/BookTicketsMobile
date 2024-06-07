package com.example.bookticketsmobile.Adapter

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
            txtNameMovieL.text = currentFilm.tenBoPhim
            txtCategoryL.text = currentFilm.theLoai
            txtDescribeL.text = currentFilm.moTa
            txtTimeL.text = currentFilm.thoiLuong?.toFormattedTime()
            txtDateOutL.text = currentFilm.thoiGianRaRap

            currentFilm.HinhAnh?.let {
                val bitmap = BitmapFactory.decodeByteArray(it, 0, it.size)
                imgL.setImageBitmap(bitmap)
            } ?: run {
                imgL.setImageResource(R.drawable.placeholder_image) // Thay thế bằng hình ảnh placeholder nếu BLOB là null
            }
        }
    }



    fun Long.toFormattedTime(): String {
        val hours = this / 60
        val minutes = this % 60
        return String.format("%02d:%02d", hours, minutes)
    }
}