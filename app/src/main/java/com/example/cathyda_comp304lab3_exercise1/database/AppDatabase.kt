package com.example.cathyda_comp304lab3_exercise1.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.cathyda_comp304lab3_exercise1.database.hospital.*

@Database(entities = [Patient::class, Nurse::class, Test::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun hospitalDao(): HospitalDao
}