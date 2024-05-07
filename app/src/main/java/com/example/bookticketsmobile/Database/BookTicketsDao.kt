package com.example.bookticketsmobile.Database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BookTicketsDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun register(khachHang: List<BookTicketsEntity.KhachHang>):Long


    @Query("SELECT * FROM phim ORDER BY idPhim ASC")
    fun readAllPhim(): LiveData<List<BookTicketsEntity.Phim>>


}

