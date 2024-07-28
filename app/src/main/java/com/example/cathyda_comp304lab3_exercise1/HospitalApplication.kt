package com.example.cathyda_comp304lab3_exercise1

import android.app.Application
import com.example.cathyda_comp304lab3_exercise1.database.HospitalDatabase

class HospitalApplication: Application() {
    val database: HospitalDatabase by lazy { HospitalDatabase.getDatabase(this) }
}