package com.example.cathyda_comp304lab3_exercise1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cathyda_comp304lab3_exercise1.database.HospitalDatabase

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val nurseIdInput: TextView = findViewById<View>(R.id.txtInputUserId) as TextView
        val nursePasswordInput: TextView = findViewById<View>(R.id.txtInputPassword) as TextView

        val db = HospitalDatabase.getDatabase(this)

        val btnLogin: Button = findViewById<View>(R.id.btnLogin) as Button
        btnLogin.setOnClickListener {
            val nursePassword = db.hospitalDao().getNursePassword(nurseIdInput.text.toString())

            if(nursePassword == nursePasswordInput) {
                val intent = Intent(this, PatientActivity::class.java)
                startActivity(intent)
            }
            else {
                Toast.makeText(this, "Incorrect password", Toast.LENGTH_SHORT)
                    .show()
            }
        }

        val btnCreateNew: Button = findViewById<View>(R.id.btnCreate) as Button
        btnCreateNew.setOnClickListener {
            val intent = Intent(this, NewNurseActivity::class.java)
            startActivity(intent)
        }
    }
}