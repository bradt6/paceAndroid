package com.example.vitamon

import android.app.Activity
import android.app.SearchManager
import android.content.Context
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
import android.support.v7.widget.SearchView
import retrofit2.Response



class PatientHomeActivity : AppCompatActivity() {

    var searchView : SearchView? = null
    var adapt: RecyclerAdaptor? = null
    lateinit var trythis: MutableList<PatientClass>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.users_fragment)

        refreshLayout.setOnRefreshListener {
            fetchMovies()
        }
        fetchMovies()
    }

    fun fetchMovies() {
        refreshLayout.isRefreshing = true

        RetrofitClient.instance.getPatients().enqueue(object : Callback<allPatients> {
            override fun onFailure(call: Call<allPatients>, t: Throwable) {
                refreshLayout.isRefreshing = false
                Toast.makeText(applicationContext, t.message, Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<allPatients>, response: Response<allPatients>) {
                refreshLayout.isRefreshing = false
                val patientList = response.body()?.users
                patientList?.let {
                    showPatients(it)
                    trythis = it
                }

            }

        })
    }

    fun showPatients(patients: MutableList<PatientClass>) {
        recycler_view.layoutManager = LinearLayoutManager(this)
        recycler_view.adapter = RecyclerAdaptor(patients)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.toolbar_menu, menu)

//        val searchManager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
//        searchView = menu.findItem(R.id.search_action).actionView as SearchView
//        searchView!!.setSearchableInfo(searchManager.getSearchableInfo(componentName))
//        searchView!!.maxWidth = Integer.MAX_VALUE
//
//        searchView!!.setOnQueryTextListener(object: SearchView.OnQueryTextListener {
//            override fun onQueryTextSubmit(query: String?): Boolean {
//                adapt!!.filter.filter(query)
//                return false
//            }
//
//            override fun onQueryTextChange(newText: String?): Boolean {
//                adapt!!.filter.filter(newText)
//                return false
//            }
//
//        })

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.addPatient_action) {
            val intent = Intent(this, addPatient::class.java)
            startActivity(intent)
        }
        return true
    }

}


