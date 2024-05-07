package com.example.bookticketsmobile.Database
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import java.sql.Date
class BookTicketsEntity() {
    // Entity cho bảng khachHang
    @Entity(tableName = "khachHang")
    data class KhachHang(
        @PrimaryKey(autoGenerate = true) val idkhachhang: Int,
        @ColumnInfo(name = "hoVaTen") val hoVaTen: String?,
        @ColumnInfo(name = "email") val email: String?,
        @ColumnInfo(name = "soDienThoai") val soDienThoai: String?,
        @ColumnInfo(name = "matKhau") val matKhau: String?,
        @ColumnInfo(name = "ngaySinh") val ngaySinh: Long?,
        @ColumnInfo(name = "gioiTinh") val gioiTinh: String?
    )

    // Entity cho bảng phim
    @Entity(
        tableName = "phim",
        foreignKeys = [ForeignKey(
            entity = KhachHang::class,
            parentColumns = ["idkhachhang"],
            childColumns = ["idKhachHang"],
            onDelete = ForeignKey.CASCADE
        )]
    )
    data class Phim(
        @PrimaryKey(autoGenerate = true) val idPhim: Int,
        @ColumnInfo(name = "tenBoPhim") val tenBoPhim: String?,
        @ColumnInfo(name = "theLoai") val theLoai: String?,
        @ColumnInfo(name = "moTa") val moTa: String?,
        @ColumnInfo(name = "thoiLuong") val thoiLuong: Long?,
        @ColumnInfo(name = "thoiGianRaRap") val thoiGianRaRap: String?,
        @ColumnInfo(name = "hinhAnh") val HinhAnh: Byte?,
        @ColumnInfo(name = "idKhachHang") val idKhachHang: Int
    )

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
                entity = cumRap::class,
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

    @Entity(tableName = "cumRap")
    data class cumRap(
        @PrimaryKey(autoGenerate = true) val idCumRap: Int,
        @ColumnInfo(name = "tinhTP") val tinhTP: String?,
        @ColumnInfo(name = "tenCumRap") val tenCumRap: String?,
    )

    @Entity(tableName = "khuyenMai")
    data class khuyenMai(
        @PrimaryKey(autoGenerate = true) val idKM: Int,
        @ColumnInfo(name = "phamTram") val phanTram: Double?,
        @ColumnInfo(name = "noiDung") val noiDung: String?,
    )

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
        val idKM: Int,
        val idCumRap: Int
    )

    @Entity(tableName = "cbDoAn")
    data class cbDoan(
        @PrimaryKey(autoGenerate = true) val idDoAn: Int,
        @ColumnInfo(name = "tenDoAn") val tenDoAn: String?,
        @ColumnInfo(name = "gia") val gia: Double?,

        )

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
                entity = cbDoan::class,
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
}