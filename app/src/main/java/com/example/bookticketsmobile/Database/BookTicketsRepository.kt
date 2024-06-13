package com.example.bookticketsmobile.Database

import com.example.bookticketsmobile.Model.CumRap_cbDoAn
import com.example.bookticketsmobile.Model.CumRap_khuyenMai
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.Model.cbDoAn
import com.example.bookticketsmobile.Model.cumRap
import com.example.bookticketsmobile.Model.khachHang
import com.example.bookticketsmobile.Model.khuyenMai
import com.example.bookticketsmobile.Model.suatChieu

class BookTicketsRepository(private  val db:BookTicketsDatabase) {

    suspend fun register(kh: khachHang) = db.bookTicketsDao().register(kh)

    suspend fun addMovies(mv: Phim) = db.bookTicketsDao().addMovies(mv)

    suspend fun addCinameClusters(cr: cumRap) = db.bookTicketsDao().addCinameClusters(cr)

    suspend fun addCbFood(cb: cbDoAn) = db.bookTicketsDao().addCbFood(cb)

    suspend fun addCbFood_CumRap(fc:CumRap_cbDoAn)= db.bookTicketsDao().addCbFood_CumRap(fc)

    suspend fun addVorcher(vc:khuyenMai)= db.bookTicketsDao().addVorcher(vc)

    suspend fun addVorcher_cumRap(vcr:CumRap_khuyenMai) = db.bookTicketsDao().addVorcher_CumRap(vcr)

    suspend fun addSuatChieu(sc: suatChieu)= db.bookTicketsDao().addSuatChieu(sc)

    fun getAllMovies() = db.bookTicketsDao().readAllPhim()

    fun getAllcbFood() = db.bookTicketsDao().readAllCbFood()

    fun getAllcinameClusters() = db.bookTicketsDao().readAllCumRap()

    fun getAllVorcher()=db.bookTicketsDao().readAllVorcher()

    fun getAllCustomer() = db.bookTicketsDao().readAllkh()
    fun getAllPerformance() = db.bookTicketsDao().readAllPerformance()
    suspend fun deleteMovies(idPhim:List<Int>) = db.bookTicketsDao().deleteById(idPhim)
}