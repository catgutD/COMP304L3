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

        supportFragmentManager.beginTransaction()
            .replace(R.id.frgNewPatient, NewPatientFragment())
            .commit()

        val btnViewPatient: Button = findViewById<View>(R.id.btnViewPatient) as Button
        btnViewPatient.setOnClickListener {
            val intent = Intent(this, UpdateInfoActivity::class.java)
            startActivity(intent)
        }

        val btnNewPatient: Button = findViewById<View>(R.id.btnNewPatient) as Button
        btnNewPatient.setOnClickListener {
            btnNewPatient.visibility = View.INVISIBLE
            btnViewPatient.visibility = View.INVISIBLE
            findViewById<View>(R.id.frgNewPatient).visibility = View.VISIBLE
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_options, menu)
        return true
    }
}