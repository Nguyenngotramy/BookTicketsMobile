package com.example.bookticketsmobile.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "suatChieu",
    foreignKeys = [
        ForeignKey(
            entity = Phim::class,
            parentColumns = ["idPhim"],
            childColumns = ["idPhim"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity =cumRap::class,
            parentColumns = ["idCumRap"],
            childColumns = ["idCumRap"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class suatChieu(
    @PrimaryKey(autoGenerate = true) val idSuatChieu: Int,
    @ColumnInfo(name = "ngayChieu") val ngayChieu: Long?,
    @ColumnInfo(name = "thoiGianChieu") val thoiGianChieu: Long?,
    @ColumnInfo(name = "phongChieu") val phongChieu: String?,
    @ColumnInfo(name = "soLuongChoNgoi") val soLuong: Int?,
    @ColumnInfo(name = "idPhim") val idPhim: Int,
    @ColumnInfo(name = "idCumRap") val idCumRap: Int
)
