package com.example.vitamon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

//    val usernameTextField = userName
//    val passwordTextField = enterPassword

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



    }

    fun username(view: View) {

    }

    fun password(view: View) {

    }

    fun submitOnClick(view: View) {
        val myIntent = Intent(this, PatientHomeActivity::class.java)
        startActivity(myIntent)
    }
}
