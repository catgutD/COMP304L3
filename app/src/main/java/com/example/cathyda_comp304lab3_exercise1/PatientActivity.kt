package com.example.cathyda_comp304lab3_exercise1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class PatientActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient)

        val btnViewPatient: Button = findViewById<View>(R.id.btnViewPatient) as Button
        btnViewPatient.setOnClickListener {
            val intent = Intent(this, UpdateInfoActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_options, menu)
        return true
    }
}