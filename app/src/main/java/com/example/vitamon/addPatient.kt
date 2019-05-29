package com.example.vitamon

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_add_patient.*
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_patient_home.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class addPatient : AppCompatActivity(){

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_patient)



    }

    fun userSignUp() {
        val email = emailInput.text.toString().trim()
        val firstName = firstNameInput.text.toString().trim()
        val lastName = lastNameInput.text.toString().trim()
        val gender = genderInput.text.toString().trim()
        val idDevice = deviceIDInput.text.toString().trim().toInt()
        val medicareNumber = medicareNumberInput.text.toString().trim().toInt()
        val phoneNumber = phoneNumberInput.text.toString().trim().toInt()
        val postcode = postcodeInput.text.toString().trim().toInt()
        val state = stateInput.text.toString().trim()
        val streetName = streetNameInput.text.toString().trim()
        val streetNumber = streetNumberInput.text.toString().trim()
        val suburb = suburbInput.text.toString().trim()

        if (email.isEmpty()) {
            emailInput.error = "Enter a Valid Email"
            emailInput.requestFocus()
            return
        }

        if (firstName.isEmpty()) {
            firstNameInput.error = "Enter a Valid Email"
            firstNameInput.requestFocus()
            return
        }

        if (lastName.isEmpty()) {
            lastNameInput.error = "Enter a Valid Email"
            lastNameInput.requestFocus()
            return
        }

        if (gender.isEmpty()) {
            genderInput.error = "Enter a Valid Email"
            genderInput.requestFocus()
            return
        }

//        if (idDevice.isEmpty()) {
//            deviceIDInput.error = "Enter a Valid Email"
//            deviceIDInput.requestFocus()
//            return
//        }
//
//        if (medicareNumber.isEmpty()) {
//            medicareNumberInput.error = "Enter a Valid Email"
//            medicareNumberInput.requestFocus()
//            return
//        }
//
//        if (phoneNumber.isEmpty()) {
//            phoneNumberInput.error = "Enter a Valid Email"
//            phoneNumberInput.requestFocus()
//            return
//        }
//
//        if (postcode.isEmpty()) {
//            postcodeInput.error = "Enter a Valid Email"
//            postcodeInput.requestFocus()
//            return
//        }

        if (state.isEmpty()) {
            stateInput.error = "Enter a Valid Email"
            stateInput.requestFocus()
            return
        }

        if (streetName.isEmpty()) {
            streetNameInput.error = "Enter a Valid Email"
            streetNameInput.requestFocus()
            return
        }

        if (streetNumber.isEmpty()) {
            streetNumberInput.error = "Enter a Valid Email"
            streetNumberInput.requestFocus()
            return
        }

        if (suburb.isEmpty()) {
            suburbInput.error = "Enter a Valid Email"
            suburbInput.requestFocus()
            return
        }

        RetrofitClient.instance.createPatient(email, firstName, gender, idDevice , lastName, medicareNumber, phoneNumber, postcode, state, streetNumber, suburb, streetName)
            .enqueue(object : Callback<DefaultResponse>{
                override fun onFailure(call: Call<DefaultResponse>, t: Throwable) {
                    Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
                }

                override fun onResponse(call: Call<DefaultResponse>, response: Response<DefaultResponse>) {
                        Toast.makeText(applicationContext, response.body()?.message, Toast.LENGTH_LONG).show()
                        addedSuccesfully()
                }

            })
    }

    fun submitOnClick(view: View) {
        userSignUp()

    }

    fun addedSuccesfully() {
        Thread.sleep(1000)
        val myIntent = Intent(applicationContext, PatientHomeActivity::class.java)
        startActivity(myIntent)
    }


}
