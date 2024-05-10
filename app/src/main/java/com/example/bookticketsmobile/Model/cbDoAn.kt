package com.example.bookticketsmobile.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cbDoAn")
data class cbDoAn(
    @PrimaryKey(autoGenerate = true) val idDoAn: Int,
    @ColumnInfo(name = "tenDoAn") val tenDoAn: String?,
    @ColumnInfo(name = "gia") val gia: Double?,
    @ColumnInfo(name = "hinhAnh") val HinhAnh: Byte?

)