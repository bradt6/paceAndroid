package com.example.vitamon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_patient_home.*

class PatientHomeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_patient_home)

        patientList.setOnItemClickListener { _, _, _, _ ->

        }
    }

    fun addUserButton(view: View) {
        val intent = Intent(this, addPatient::class.java)
        startActivity(intent)
    }

    fun searchPatientRecords(view: View) {
        doQuery()
    }

    fun doQuery() {
        val name = search_text_field.text.toString()

        val query = "SELECT firstName, lastName FROM Patient WHERE name = '$name' "

    }

}
