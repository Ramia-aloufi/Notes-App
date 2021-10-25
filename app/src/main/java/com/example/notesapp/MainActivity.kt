package com.example.notesapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var btn:Button
    lateinit var et1:EditText
    var note = ""
    var nn = ""
    lateinit var rv:RecyclerView
    lateinit var all:ArrayList<String>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.button)
        et1 = findViewById(R.id.editTextTextPersonName)
        all = arrayListOf()
        rv = findViewById(R.id.rv)
        var dbhlpr = DBHelper(this)
        all = dbhlpr.retriveData()
        rv.adapter?.notifyDataSetChanged()



        Log.d("uuu","${dbhlpr.retriveData()}")


        btn.setOnClickListener {

            note = et1.text.toString()
            if(note.isNotEmpty()) {
                var status = dbhlpr.saveddata(note)
                it.hideKeyboard()
                Toast.makeText(this, "data Saved successfully +$status", Toast.LENGTH_SHORT).show()
                all.add(note)
                rv.adapter?.notifyDataSetChanged()
                et1.text.clear()
            }else{
                Toast.makeText(this, "Type a note", Toast.LENGTH_SHORT).show()

            }

        }
        rv.adapter = MyAdapter(all)
        rv.layoutManager = LinearLayoutManager(this)
    }
    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}