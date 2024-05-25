package com.example.bookticketsmobile.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "Phim")
data class Phim(
    @PrimaryKey(autoGenerate = true) val idPhim: Int,
    @ColumnInfo(name = "tenBoPhim") val tenBoPhim: String?,
    @ColumnInfo(name = "theLoai") val theLoai: String?,
    @ColumnInfo(name = "moTa") val moTa: String?,
    @ColumnInfo(name = "thoiLuong") val thoiLuong: Long?,
    @ColumnInfo(name = "thoiGianRaRap") val thoiGianRaRap: String?,
    @ColumnInfo(name = "hinhAnh") val HinhAnh: ByteArray?
)