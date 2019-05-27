package com.example.vitamon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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
        val usernameText = userName.text.toString().trim()
        val passwordText = enterPassword.text.toString().trim()

        if (usernameText.isEmpty()) {
            userName.error = "Enter a Valid Email"
            userName.requestFocus()
            return
        }

        if (passwordText.isEmpty()) {
            enterPassword.error = "Enter a Valid Email"
            enterPassword.requestFocus()
            return
        }

        RetrofitClient.instance.userlogin(usernameText, passwordText).enqueue(object: Callback<LoginResponseClass>{
            override fun onFailure(call: Call<LoginResponseClass>, t: Throwable) {
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<LoginResponseClass>, response: Response<LoginResponseClass>) {
                println(response)
                if (!response.body()?.error!!) {

                    SharedPrefManager.getInstance(applicationContext).saveUser(response.body()?.user!!)

                    val myIntent = Intent(applicationContext, PatientHomeActivity::class.java)
                    myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
                    startActivity(myIntent)

                } else {
                    Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                }
            }

        })
    }

    override fun onStart() {
        super.onStart()

        if(SharedPrefManager.getInstance(this).isLoggedIn) {
            val myIntent = Intent(applicationContext, PatientHomeActivity::class.java)
            myIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(myIntent)
        }
    }

}
