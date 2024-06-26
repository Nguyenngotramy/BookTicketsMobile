package com.example.bookticketsmobile.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "choNgoi",
    foreignKeys = [
        ForeignKey(
            entity = suatChieu::class,
            parentColumns = ["idSuatChieu"],
            childColumns = ["idSuatChieu"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = khachHang::class,
            parentColumns = ["idKhachHang"],
            childColumns = ["idKhachHang"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class choNgoi(
    @PrimaryKey(autoGenerate = true) val idChoNgoi: Int,
    @ColumnInfo(name = "tenGhe") val tenGhe: String?,
    @ColumnInfo(name = "giaVe") val giaVe: Double?,
    @ColumnInfo(name = "idSuatChieu") val idSuatChieu: Int,
    @ColumnInfo(name = "idKhachHang") val idKhachHang: Int?
)
