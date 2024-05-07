package com.example.bookticketsmobile.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [BookTicketsEntity.KhachHang::class,
        BookTicketsEntity.Phim::class,
        BookTicketsEntity.suatChieu::class,
        BookTicketsEntity.choNgoi::class,
        BookTicketsEntity.cumRap::class,
        BookTicketsEntity.khuyenMai::class,
        BookTicketsEntity.CumRap_khuyenMai::class,
        BookTicketsEntity.cbDoan::class,
        BookTicketsEntity.CumRap_cbDoAn::class], version = 1, exportSchema = false)
 abstract class BookTicketsDatabase:RoomDatabase() {
     abstract fun bookTicketsDao():BookTicketsDao

     companion object{
         @Volatile
         private var INSTANCE: BookTicketsDatabase? = null

         fun getDatabase(context: Context):BookTicketsDatabase{
             val tempInstance = INSTANCE
             if (tempInstance != null){
                 return  tempInstance
             }
             synchronized(this){
                 val instance = Room.databaseBuilder(
                     context.applicationContext,
                     BookTicketsDatabase::class.java,
                     "book_tickets_data"
                 ).build()
                 INSTANCE= instance
                 return  instance
             }
         }
     }
}