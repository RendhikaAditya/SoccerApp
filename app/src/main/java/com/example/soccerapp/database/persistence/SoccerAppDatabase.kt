package com.example.soccerapp.database.persistence

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

//@Database(
//    entities = [],
//    exportSchema = false,
//    version = 1
//)
abstract class SoccerAppDatabase: RoomDatabase() {

    companion object {
        @Volatile private var instance: SoccerAppDatabase? = null
        private val LOCK = Any()

        operator fun invoke(context: Context) = instance ?: synchronized(LOCK) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) =
                Room.databaseBuilder(context.applicationContext,
                        SoccerAppDatabase::class.java, "CekOngkirLazday.db")
                        .allowMainThreadQueries()
                        .build()
    }
}