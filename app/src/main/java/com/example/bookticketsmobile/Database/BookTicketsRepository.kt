package com.example.bookticketsmobile.Database

import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.Model.cbDoAn
import com.example.bookticketsmobile.Model.cumRap
import com.example.bookticketsmobile.Model.khachHang

class BookTicketsRepository(private  val db:BookTicketsDatabase) {

    suspend fun register(kh: khachHang) = db.bookTicketsDao().register(kh)

    suspend fun addMovies(mv: Phim) = db.bookTicketsDao().addMovies(mv)

    suspend fun addCinameClusters(cr: cumRap) = db.bookTicketsDao().addCinameClusters(cr)

    suspend fun addCbFood(cb: cbDoAn) = db.bookTicketsDao().addCbFood(cb)

    fun getAllMovies() = db.bookTicketsDao().readAllPhim()

    fun getAllcbFood() = db.bookTicketsDao().readAllCbFood()

    fun getAllcinameClusters() = db.bookTicketsDao().readAllCumRap()
    suspend fun deleteMovies(idPhim:List<Int>) = db.bookTicketsDao().deleteById(idPhim)
}