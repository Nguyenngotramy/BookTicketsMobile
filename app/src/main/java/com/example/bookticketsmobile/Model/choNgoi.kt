package com.example.bookticketsmobile.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "choNgoi",
    foreignKeys = [ForeignKey(
        entity = suatChieu::class,
        parentColumns = ["idSuatChieu"],
        childColumns = ["idSuatChieu"],
        onDelete = ForeignKey.CASCADE
    )]
)
data class choNgoi(
    @PrimaryKey(autoGenerate = true) val idChoNgoi: Int,
    @ColumnInfo(name = "soHang") val soHang: String?,
    @ColumnInfo(name = "soGhe") val soGhe: Int?,
    @ColumnInfo(name = "giaVe") val giaVe: Double?,
    @ColumnInfo(name = "idSuatChieu") val idSuatChieu: Int // Khóa ngoại đến bảng suatChieu
)