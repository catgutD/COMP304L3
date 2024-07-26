package com.example.cathyda_comp304lab3_exercise1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnLogin: Button = findViewById<View>(R.id.btnLogin) as Button
        btnLogin.setOnClickListener {
            val intent = Intent(this, PatientActivity::class.java)
            startActivity(intent)
        }

        val btnCreateNew: Button = findViewById<View>(R.id.btnCreate) as Button
        btnCreateNew.setOnClickListener {
            val intent = Intent(this, NewNurseActivity::class.java)
            startActivity(intent)
        }
    }
}