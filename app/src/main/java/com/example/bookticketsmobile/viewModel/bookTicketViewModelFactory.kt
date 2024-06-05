package com.example.bookticketsmobile.viewModel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.bookticketsmobile.Database.BookTicketsRepository

class bookTicketViewModelFactory (val app: Application, private val btrepository: BookTicketsRepository): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return bookTicketViewModel(app,btrepository) as T
    }

}