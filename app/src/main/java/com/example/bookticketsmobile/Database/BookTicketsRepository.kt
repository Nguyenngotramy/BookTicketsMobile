package com.example.bookticketsmobile.Database

import com.example.bookticketsmobile.Model.khachHang

class BookTicketsRepository(private  val db:BookTicketsDatabase) {

    suspend fun register(kh: khachHang) = db.bookTicketsDao().register(kh)

    fun getAllMovies() = db.bookTicketsDao().readAllPhim()
}