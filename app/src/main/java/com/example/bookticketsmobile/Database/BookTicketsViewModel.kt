/*
package com.example.bookticketsmobile.Database

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BookTicketsViewModel(application :Application):AndroidViewModel(application) {

    private val readAllData : LiveData<List<BookTicketsEntity.Phim>>
    private val repository:BookTicketsRepository

    init{
        val btDao = BookTicketsDatabase.database(application).bookTicketsDao()
        repository = BookTicketsRepository(btDao)
        readAllData = repository.readAllPhim
    }

    fun register(kh:BookTicketsEntity.KhachHang){
        viewModelScope.launch(Dispatchers.IO){
            repository.callRegister(kh)
        }
    }

}*/
