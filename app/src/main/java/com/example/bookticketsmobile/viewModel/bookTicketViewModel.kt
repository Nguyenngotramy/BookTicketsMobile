package com.example.bookticketsmobile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.Model.CumRap_cbDoAn
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.Model.cbDoAn
import com.example.bookticketsmobile.Model.cumRap
import com.example.bookticketsmobile.Model.khachHang
import kotlinx.coroutines.launch

class bookTicketViewModel(app: Application, private val btrepostory: BookTicketsRepository) : AndroidViewModel(app){

        fun register(kh: khachHang) = viewModelScope.launch {
            btrepostory.register(kh)
        }
        fun addMovies(mv: Phim) = viewModelScope.launch {
            btrepostory.addMovies(mv)
        }
        fun addCinameClusters(cr: cumRap) = viewModelScope.launch {
        btrepostory.addCinameClusters(cr)
        }

         fun addCbFood(cb: cbDoAn) = viewModelScope.launch {
             btrepostory.addCbFood(cb)
         }
         fun addCbFood_CumRap(fc:CumRap_cbDoAn) = viewModelScope.launch {
             btrepostory.addCbFood_CumRap(fc)
         }
        fun deleteMovies(id: List<Int>) = viewModelScope.launch {
        btrepostory.deleteMovies(id)
        }
         fun updateMovies(mv: Phim) = viewModelScope.launch {
        btrepostory.addMovies(mv)
         }
    fun getAllFilm() = btrepostory.getAllMovies()
    fun getAllFood() = btrepostory.getAllcbFood()

    fun getAllCinameCluster() = btrepostory.getAllcinameClusters()
    }