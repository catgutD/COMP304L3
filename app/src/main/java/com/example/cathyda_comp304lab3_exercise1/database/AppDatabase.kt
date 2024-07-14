package com.example.cathyda_comp304lab3_exercise1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cathyda_comp304lab3_exercise1.database.hospital.*

@Database(entities = [Patient::class, Nurse::class, Test::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun hospitalDao(): HospitalDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context):AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    AppDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/hospital.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}