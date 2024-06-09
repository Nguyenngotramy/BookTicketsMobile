package com.example.bookticketsmobile.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "khuyenMai")
data class khuyenMai(
    @PrimaryKey val idKM: String,
    @ColumnInfo(name = "phamTram") val phanTram: Double?,
    @ColumnInfo(name = "noiDung") val noiDung: String?,
)
