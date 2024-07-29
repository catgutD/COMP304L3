package com.example.cathyda_comp304lab3_exercise1.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.cathyda_comp304lab3_exercise1.database.hospital.HospitalDao
import com.example.cathyda_comp304lab3_exercise1.database.hospital.NurseEntity
import com.example.cathyda_comp304lab3_exercise1.database.hospital.PatientEntity
import com.example.cathyda_comp304lab3_exercise1.database.hospital.TestEntity

@Database(entities = arrayOf(PatientEntity::class, NurseEntity::class, TestEntity::class), version = 1)
abstract class HospitalDatabase: RoomDatabase() {
    abstract fun hospitalDao(): HospitalDao

    companion object {
        @Volatile
        private var INSTANCE: HospitalDatabase? = null

        fun getDatabase(context: Context): HospitalDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context,
                    HospitalDatabase::class.java,
                    "app_database")
                    .build()
                INSTANCE = instance

                instance
            }
        }
    }
}