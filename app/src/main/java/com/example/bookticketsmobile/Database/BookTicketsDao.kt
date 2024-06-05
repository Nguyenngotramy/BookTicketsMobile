package com.example.bookticketsmobile.Database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.Model.khachHang

@Dao
interface BookTicketsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun register(kh: khachHang)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(mv: Phim)

    @Query("SELECT * FROM phim ORDER BY idPhim ASC")
    fun readAllPhim(): LiveData<List<Phim>>


}

