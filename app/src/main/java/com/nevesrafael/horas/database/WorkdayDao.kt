package com.nevesrafael.horas.database

import androidx.room.*
import com.nevesrafael.horas.model.Workday

@Dao
interface WorkdayDao {

    @Query("SELECT * FROM Workday")
    fun buscaTodos(): List<Workday>

    @Insert
    fun salvar(evento: Workday)

    @Delete
    fun remove(evento: Workday)

    @Update
    fun altera(evento: Workday)

    @Query("SELECT * FROM Workday WHERE id = :id")
    fun buscaPorId(id: Int): Workday?
}