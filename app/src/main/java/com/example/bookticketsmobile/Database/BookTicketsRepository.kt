package com.example.bookticketsmobile.Database

import androidx.lifecycle.LiveData

class BookTicketsRepository(private  val bt:BookTicketsDao) {
    val readAllPhim:LiveData<List<BookTicketsEntity.Phim>> = bt.readAllPhim()

    suspend fun callRegister(kh: BookTicketsEntity.KhachHang) {
        bt.register(kh)
    }

}