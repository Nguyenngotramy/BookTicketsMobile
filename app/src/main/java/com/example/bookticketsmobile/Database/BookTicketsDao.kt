package com.example.bookticketsmobile.Database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.Model.cbDoAn
import com.example.bookticketsmobile.Model.cumRap
import com.example.bookticketsmobile.Model.khachHang

@Dao
interface BookTicketsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun register(kh: khachHang)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(mv: Phim)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCbFood(cb: cbDoAn)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCinameClusters(cr: cumRap)

    @Query("SELECT * FROM Phim ORDER BY idPhim ASC")
    fun readAllPhim(): LiveData<List<Phim>>

    @Query("SELECT * FROM cbDoAn")
    fun readAllCbFood(): LiveData<List<cbDoAn>>

    @Query("SELECT * FROM cumRap")
    fun readAllCumRap(): LiveData<List<cumRap>>

     @Query("DELETE FROM Phim WHERE idPhim IN (:idList)")
        fun deleteById(idList: List<Int>)



}

