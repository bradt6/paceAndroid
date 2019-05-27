package com.example.vitamon

import android.content.Context
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_patient_home.view.*

class RecyclerAdaptor (var patientList: List<PatientClass>) : RecyclerView.Adapter<RecyclerAdaptor.ViewHolder>() {


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.activity_patient_home, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return patientList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val patient = patientList[p1]
//        p0.setData(patient, p1)
        p0.itemView.textFirstName.text = patient.firstName
        p0.itemView.textidDevice.text = patient.idDevice.toString()
//        p0.nameOfPatient?.text = patient.firstName
//        p0.idDevice?.text = patient.idDevice.toString()
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

//        fun setData (patient: PatientClass, pos: Int) {
//            itemView.textFirstName.text = patient!!.firstName
//            itemView.textidDevice.text = patient!!.idDevice.toString()
//        }
//
//        var nameOfPatient = itemView.findViewById<TextView>(R.id.textFirstName)
//        var idDevice = itemView.findViewById<TextView>(R.id.textidDevice)
    }
}

