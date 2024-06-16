package com.example.bookticketsmobile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.bookticketsmobile.databinding.FilmlistItemBinding

private lateinit var binding: FilmlistItemBinding
//private lateinit var layout:
class FilmViewRecycle (var listFilm: List<FilmDataHome>, var listener: OnFilmClickListener): RecyclerView.Adapter<FilmViewRecycle.FilmViewHolder>(){
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
            var filmCard = findViewById<CardView>(R.id.filmCard)

            imageFilm.setImageResource(listFilm[position].imageFilm)
            nameFilm.text = listFilm[position].nameFilm
            filmCard.id = listFilm[position].idFilm

            bookBtn.setOnClickListener{
                listener.onFilmClick(listFilm[position])
            }
        }
    }


    override fun getItemCount(): Int {
        return listFilm.size
    }
}