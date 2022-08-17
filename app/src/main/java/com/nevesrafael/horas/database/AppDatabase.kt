package com.nevesrafael.horas.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.nevesrafael.horas.model.Workday

@Database(
    entities = [Workday::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun workdayDao(): WorkdayDao


    companion object {

        fun instance(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context,
                AppDatabase::class.java,
                "aFazer.db"
            ).allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}