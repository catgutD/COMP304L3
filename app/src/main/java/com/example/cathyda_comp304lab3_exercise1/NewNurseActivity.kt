package com.example.cathyda_comp304lab3_exercise1

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.cathyda_comp304lab3_exercise1.database.HospitalDatabase
import com.example.cathyda_comp304lab3_exercise1.database.hospital.NurseEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch
import org.w3c.dom.Text

class NewNurseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_nurse)

        val nurseIdInput: TextView = findViewById<View>(R.id.txtInputNurseID) as TextView
        val nurseFirstNameInput: TextView = findViewById<View>(R.id.txtInputNewNurseFirstName) as TextView
        val nurseLastNameInput: TextView = findViewById<View>(R.id.txtInputNewNurseLastName) as TextView
        val nurseDepartmentInput: TextView = findViewById<View>(R.id.txtInputNewNurseDepartment) as TextView
        val nursePasswordInput: TextView = findViewById<View>(R.id.txtInputNewNursePassword) as TextView

        val db = HospitalDatabase.getDatabase(this)

        val btnCreate: Button = findViewById<View>(R.id.btnCreateNurse) as Button
        btnCreate.setOnClickListener {
            CoroutineScope(IO).launch {

                val nurseID: String = nurseIdInput.text.toString()
                val nurseFirstName: String = nurseFirstNameInput.text.toString()
                val nurseLastName: String = nurseLastNameInput.text.toString()
                val nurseDepartment: String = nurseDepartmentInput.text.toString()
                val nursePassword: String = nursePasswordInput.text.toString()

                db.hospitalDao().insertNewNurse(NurseEntity(nurseID, nurseFirstName, nurseLastName, nurseDepartment, nursePassword))
            }
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
}