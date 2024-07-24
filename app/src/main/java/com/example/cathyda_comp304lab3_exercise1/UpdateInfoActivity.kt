package com.example.cathyda_comp304lab3_exercise1

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class UpdateInfoActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update_info)

        val btnViewTest: Button = findViewById<View>(R.id.btnViewTest) as Button
        btnViewTest.setOnClickListener {
            val intent = Intent(this, ViewTestInfoActivity::class.java)
            startActivity(intent)
        }

        val btnUpdate: Button = findViewById<View>(R.id.btnUpdate) as Button
        btnUpdate.setOnClickListener {

        }

        val btnNewTest: Button = findViewById<View>(R.id.btnNewTest) as Button
        btnNewTest.setOnClickListener {
            val intent = Intent(this, TestActivity::class.java)
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