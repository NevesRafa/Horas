package com.nevesrafael.horas.database

import androidx.room.*
import com.nevesrafael.horas.model.Workday

@Dao
interface WorkdayDao {

    @Query("SELECT * FROM Workday")
    fun searchAll(): List<Workday>

    @Insert
    fun save(evento: Workday)

    @Delete
    fun remove(evento: Workday)

    @Update
    fun update(evento: Workday)

    @Query("SELECT * FROM Workday WHERE id = :id")
    fun searchId(id: Int): Workday

//    @Query("SELECT * FROM Workday WHERE date LIKE % :date")
//    fun searchDate(date: String): Workday
}