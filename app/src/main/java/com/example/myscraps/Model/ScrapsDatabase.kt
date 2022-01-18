package com.example.myscraps.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Scraps::class], version = 1, exportSchema = false)
abstract class ScrapsDatabase : RoomDatabase() {

    abstract fun myScrapsDao(): ScrapsDao

    companion object {

        @Volatile
        var INSTANCE: ScrapsDatabase? = null

        fun getDatabaseInstance(context: Context): ScrapsDatabase {

            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val roomDatabaseInstance =
                    Room.databaseBuilder(context, ScrapsDatabase::class.java, "table_scraps").build()
                INSTANCE = roomDatabaseInstance
                return roomDatabaseInstance
            }
        }
    }
}