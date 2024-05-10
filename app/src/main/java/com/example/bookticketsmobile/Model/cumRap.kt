package com.example.bookticketsmobile.Model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cumRap")
data class cumRap(
    @PrimaryKey(autoGenerate = true) val idCumRap: Int,
    @ColumnInfo(name = "tinhTP") val tinhTP: String?,
    @ColumnInfo(name = "tenCumRap") val tenCumRap: String?
)
