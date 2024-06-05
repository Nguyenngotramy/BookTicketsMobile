package com.example.bookticketsmobile.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "khachHang")
data class khachHang(
    @PrimaryKey(autoGenerate = true) val idKhachHang: Int,
    @ColumnInfo(name = "hoVaTen") val hoVaTen: String?,
    @ColumnInfo(name = "email") val email: String?,
    @ColumnInfo(name = "soDienThoai") val soDienThoai: String?,
    @ColumnInfo(name = "matKhau") val matKhau: String?,
    @ColumnInfo(name = "ngaySinh") val ngaySinh: String?,
    @ColumnInfo(name = "gioiTinh") val gioiTinh: String?
)