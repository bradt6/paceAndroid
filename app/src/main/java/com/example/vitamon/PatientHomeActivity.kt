package com.example.vitamon

import android.app.Activity
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_patient_home.*
import kotlinx.android.synthetic.main.users_fragment.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response



class PatientHomeActivity : AppCompatActivity() {

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.addPatient_action) {
            val intent = Intent(this, addPatient::class.java)
            startActivity(intent)
        }
        return true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_fragment)

        RetrofitClient.instance.getPatients().enqueue(object : Callback<allPatients> {
            override fun onFailure(call: Call<allPatients>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<allPatients>, response: Response<allPatients>) {
               val patientList = response.body()?.users
                print("PATIENT LIST")
                print(patientList)
                patientList?.let {
                    showPatients(it)
                }

            }

        })
    }

    fun showPatients(patients: List<PatientClass>) {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = RecyclerAdaptor(patients)
    }

    fun addUserButton(view: View) {
        val intent = Intent(this, addPatient::class.java)
        startActivity(intent)
    }



//    fun searchPatientRecords(view: View) {
//        doQuery()
//    }
//
//    fun doQuery() {
//        val name = search_text_field.text.toString()
//
//        val query = "SELECT firstName, lastName FROM Patient WHERE name = '$name' "
//
//    }

}


