package com.example.bookticketsmobile.Model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "CumRap_cbDoAn",
    foreignKeys = [
        ForeignKey(
            entity = cumRap::class,
            parentColumns = ["idCumRap"], // Sửa tên cột thành "idCumRap"
            childColumns = ["idCumRap"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = cbDoAn::class,
            parentColumns = ["idDoAn"],
            childColumns = ["idDoAn"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class CumRap_cbDoAn(

    @PrimaryKey(autoGenerate = true) val id:Int,
    val idDoAn: Int,
    val idCumRap: Int
)
