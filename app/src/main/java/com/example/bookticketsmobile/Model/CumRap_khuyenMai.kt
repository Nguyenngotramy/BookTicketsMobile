package com.example.bookticketsmobile.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "CumRap_khuyenMai",
    foreignKeys = [
        ForeignKey(
            entity = cumRap::class,
            parentColumns = ["idCumRap"],
            childColumns = ["idCumRap"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = khuyenMai::class,
            parentColumns = ["idKM"],
            childColumns = ["idKM"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CumRap_khuyenMai(
    @PrimaryKey(autoGenerate = true) val id:Int,
    val idKM: String,
    val idCumRap: Int
)