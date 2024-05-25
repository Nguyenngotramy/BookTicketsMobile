package com.example.bookticketsmobile.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bookticketsmobile.Model.CumRap_cbDoAn
import com.example.bookticketsmobile.Model.CumRap_khuyenMai
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.Model.cbDoAn
import com.example.bookticketsmobile.Model.choNgoi
import com.example.bookticketsmobile.Model.cumRap
import com.example.bookticketsmobile.Model.khachHang
import com.example.bookticketsmobile.Model.khuyenMai
import com.example.bookticketsmobile.Model.suatChieu

@Database(
    entities = [khachHang::class,
      Phim::class,
     suatChieu::class,
       choNgoi::class,
        cumRap::class,
       khuyenMai::class,
        CumRap_khuyenMai::class,
       cbDoAn::class,
        CumRap_cbDoAn::class], version = 1, exportSchema = false)
 abstract class BookTicketsDatabase:RoomDatabase() {
     abstract fun bookTicketsDao():BookTicketsDao

     companion object{
         @Volatile
         private var instance: BookTicketsDatabase? = null
         private val LOCK = Any()

         operator fun invoke(context: Context) = instance?:
         synchronized(LOCK){
             instance ?:
             createDatabase(context).also{
                 instance = it
             }
         }

         private fun createDatabase(context: Context)=
             Room.databaseBuilder(
                 context.applicationContext,
                 BookTicketsDatabase::class.java,
                 "bookticketsdb"
             ).build()


     }
}