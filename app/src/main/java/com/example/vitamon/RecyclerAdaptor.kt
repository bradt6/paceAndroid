package com.example.vitamon

import android.content.Context
import android.content.Intent
import android.support.v7.view.menu.ActionMenuItemView
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_patient_home.view.*

class RecyclerAdaptor (var patientList: MutableList<PatientClass>) : RecyclerView.Adapter<RecyclerAdaptor.ViewHolder>(), Filterable {

    var patientSearchList: MutableList<PatientClass>? = null


    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.activity_patient_home, p0, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return patientList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        val patient = patientList[p1]
        p0.itemView.textFirstName.text = "${patient.firstName}  ${patient.lastName}"
        p0.itemView.textidDevice.text = patient.idDevice.toString()
        p0.patient = patient

    }

    inner class ViewHolder(itemView: View, var patient: PatientClass? = null) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        init {
            itemView.setOnClickListener(this)
        }



        override fun onClick(v: View?) {
//            Log.d("RecyclerView", "CLICK!!!!")
            val context = itemView.context
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra("patient_First_Name", patient?.firstName)
            context.startActivity(intent)
        }

    }

    override fun getFilter(): Filter {
        return object: Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString()
                if (charString.isEmpty()) {
                    patientSearchList = patientList
                } else {
                    val filteredList = ArrayList<PatientClass>()
                    for (row in patientList) {
                        if (row.firstName!!.toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row)
                        }
                    }
                    patientSearchList = filteredList
                }
                val filterResults = FilterResults()
                filterResults.values = patientSearchList
                return filterResults
            }

            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                patientSearchList = results!!.values as ArrayList<PatientClass>
                notifyDataSetChanged()
            }

        }
    }
}

