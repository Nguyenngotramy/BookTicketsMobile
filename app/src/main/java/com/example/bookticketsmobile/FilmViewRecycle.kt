package com.example.bookticketsmobile

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookticketsmobile.databinding.FilmlistItemBinding

private lateinit var binding: FilmlistItemBinding
//private lateinit var layout:
class FilmViewRecycle (var listFilm: List<FilmDataHome>): RecyclerView.Adapter<FilmViewRecycle.FilmViewHolder>(){
    inner class FilmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.filmlist_item, parent, false)
        binding = FilmlistItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FilmViewHolder(binding.root)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        holder.itemView.apply {
            var imageFilm = findViewById<ImageView>(R.id.imageFilm)
            var nameFilm = findViewById<TextView>(R.id.nameFilm)
            var bookBtn = findViewById<Button>(R.id.bookBtn)

            imageFilm.setImageResource(listFilm[position].imageFilm)
            nameFilm.text = listFilm[position].nameFilm
//            bookBtn.setOnClickListener {
//                val details = Intent(requireActivity(), DetailOfFilm::class.java)
//                startActivity(details)
//            }
        }
    }


    override fun getItemCount(): Int {
        return listFilm.size
    }

//    fun clickButton(view:View) {
//
//                    bookBtn.setOnClickListener {
//                val details = Intent(, DetailOfFilm::class.java)
//                startActivity(details)
//            }
//    }
}