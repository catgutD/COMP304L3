package com.example.cathyda_comp304lab3_exercise1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cathyda_comp304lab3_exercise1.database.hospital.*

@Database(entities = [PatientEntity::class, NurseEntity::class, TestEntity::class], version = 1)
abstract class HospitalDatabase: RoomDatabase() {
    abstract fun hospitalDao(): HospitalDao

    companion object {
        @Volatile
        private var INSTANCE: HospitalDatabase? = null

        fun getDatabase(context: Context):HospitalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    HospitalDatabase::class.java,
                    "app_database")
                    .createFromAsset("database/hospital.db")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}