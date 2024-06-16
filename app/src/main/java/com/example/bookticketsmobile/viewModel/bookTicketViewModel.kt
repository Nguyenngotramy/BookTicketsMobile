package com.example.bookticketsmobile.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.example.bookticketsmobile.Database.BookTicketsRepository
import com.example.bookticketsmobile.Model.CumRap_cbDoAn
import com.example.bookticketsmobile.Model.CumRap_khuyenMai
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.Model.cbDoAn
import com.example.bookticketsmobile.Model.choNgoi
import com.example.bookticketsmobile.Model.cumRap
import com.example.bookticketsmobile.Model.khachHang
import com.example.bookticketsmobile.Model.khuyenMai
import com.example.bookticketsmobile.Model.suatChieu
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
         fun addVorcher(vc:khuyenMai) = viewModelScope.launch {
             btrepostory.addVorcher(vc)
         }
         fun addVorcher_CumRap(vcr:CumRap_khuyenMai) = viewModelScope.launch {
             btrepostory.addVorcher_cumRap(vcr)
         }

    fun addPerformance(sc:suatChieu) = viewModelScope.launch {
        btrepostory.addSuatChieu(sc)
    }
    fun addSeat(s:choNgoi) = viewModelScope.launch {
        btrepostory.addSeat(s)
    }
        fun deleteMovies(id: List<Int>) = viewModelScope.launch {
        btrepostory.deleteMovies(id)
        }
        fun updateFood(id:Int, Name:String,price:Double) =viewModelScope.launch {
            withContext(Dispatchers.IO) {
               btrepostory.updateCbDoAn(id, Name, price)
            }
        }
         fun updateMovies(mv: Phim) = viewModelScope.launch {
        btrepostory.addMovies(mv)
         }
          fun getAllFilm() = btrepostory.getAllMovies()
         fun getAllFood() = btrepostory.getAllcbFood()
         fun getAllCinameCluster() = btrepostory.getAllcinameClusters()
         fun getAllVorcher() = btrepostory.getAllVorcher()
         fun getAllCustomers() = btrepostory.getAllCustomer()
         fun getAllPerformance() = btrepostory.getAllPerformance()
         fun getAllFoodByName(nameF:String) = btrepostory.getAllFoodByName(nameF)

}