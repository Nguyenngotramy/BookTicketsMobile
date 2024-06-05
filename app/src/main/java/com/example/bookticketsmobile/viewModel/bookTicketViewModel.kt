package com.example.bookticketsmobile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.Model.khachHang
import kotlinx.coroutines.launch

class bookTicketViewModel(app: Application, private val btrepostory: BookTicketsRepository) : AndroidViewModel(app){

        fun register(kh: khachHang) = viewModelScope.launch {
            btrepostory.register(kh)
        }
        fun addMovies(mv: Phim) = viewModelScope.launch {
            btrepostory.addMovies(mv)
        }
    }