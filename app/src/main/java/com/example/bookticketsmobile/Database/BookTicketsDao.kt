package com.example.bookticketsmobile.Database


import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.bookticketsmobile.DayData
import com.example.bookticketsmobile.Model.CumRap_cbDoAn
import com.example.bookticketsmobile.Model.CumRap_khuyenMai
import com.example.bookticketsmobile.Model.Phim
import com.example.bookticketsmobile.Model.cbDoAn
import com.example.bookticketsmobile.Model.choNgoi
import com.example.bookticketsmobile.Model.cumRap
import com.example.bookticketsmobile.Model.khachHang
import com.example.bookticketsmobile.Model.khuyenMai
import com.example.bookticketsmobile.Model.suatChieu
import com.example.bookticketsmobile.Model.suatChieuDetail
import com.example.bookticketsmobile.TimeData

@Dao
interface BookTicketsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun register(kh: khachHang)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addMovies(mv: Phim)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCbFood(cb: cbDoAn)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCinameClusters(cr: cumRap)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCbFood_CumRap(fc: CumRap_cbDoAn)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVorcher(vc:khuyenMai)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addVorcher_CumRap(vcr:CumRap_khuyenMai)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSuatChieu(sc:suatChieu)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addSeat(s: choNgoi)

    @Query("SELECT * FROM Phim ORDER BY idPhim ASC")
    fun readAllPhim(): LiveData<List<Phim>>

    @Query("SELECT * FROM cbDoAn")
    fun readAllCbFood(): LiveData<List<cbDoAn>>

    @Query("SELECT * FROM cumRap")
    fun readAllCumRap(): LiveData<List<cumRap>>

    @Query("SELECT * FROM khuyenMai")
    fun readAllVorcher(): LiveData<List<khuyenMai>>

    @Query("SELECT * FROM khachHang")
    fun readAllkh(): LiveData<List<khachHang>>

    @Query("""SELECT suatchieu.idSuatChieu, suatChieu.ngayChieu,suatChieu.thoiGianChieu,suatChieu.phongChieu,suatChieu.soLuongChoNgoi,cumRap.tenCumRap, Phim.tenBoPhim FROM suatChieu 
            INNER JOIN cumRap on cumRap.idCumRap = suatChieu.idCumRap
            INNER JOIN Phim on Phim.idPhim = suatChieu.idPhim """)
    fun readAllPerformance(): LiveData<List<suatChieuDetail>>
    @Query("""SELECT cbDoAn.idDoAn, cbDoAn.tenDoAn,cbDoAn.gia,cbDoAn.hinhAnh FROM cbDoAn INNER JOIN CumRap_cbDoAn ON CumRap_cbDoAn.idDoAn = cbDoAn.idDoAn
            INNER JOIN cumRap ON cumRap.idCumRap = CumRap_cbDoAn.idCumRap WHERE cumRap.tenCumRap  IN (:nameList)""")
    fun readAllCbFoodByNameCinameCluster(nameList:String): LiveData<List<cbDoAn>>
    @Query("""SELECT * FROM cbDoAn WHERE tenDoAn LIKE (:nameList)""")
    fun readAllCbFoodByNameFood(nameList:String): LiveData<List<cbDoAn>>
    @Query("""
        SELECT suatchieu.ngayChieu
        FROM suatchieu
        INNER JOIN phim ON phim.idPhim = suatchieu.idPhim
        INNER JOIN cumrap ON cumrap.idCumRap = suatchieu.idCumRap
        WHERE phim.idPhim = :id AND cumrap.idCumRap = :idCr
    """)
    fun readAllDateByPhim(id:Int,idCr:Int): LiveData<List<DayData>>

    @Query("""
       SELECT thoiGianChieu
    from suatchieu
    inner join phim on phim.idPhim = suatchieu.idPhim
    inner join cumrap on cumrap.idCumRap = suatchieu.idCumRap
    where phim.idPhim = :idP and cumrap.idCumRap = :idCr and suatchieu.ngayChieu = :date

    """)
    fun readAlltgcByid(idP:Int,idCr:Int,date:String): LiveData<List<TimeData>>
    @Query("UPDATE cbDoAn SET tenDoAn = :tenDoAn, gia = :price WHERE idDoAn = :id")
    fun updateCbDoAn(id: Int, tenDoAn: String, price: Double)


    @Query("DELETE FROM Phim WHERE idPhim IN (:idList)")
        fun deleteById(idList: List<Int>)




}

