package com.example.notesapp

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    lateinit var btn:Button
    lateinit var et1:EditText
    var note = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.button)
        et1 = findViewById(R.id.editTextTextPersonName)

        btn.setOnClickListener {

            note = et1.text.toString()

            var dbhlpr = DBHelper(this)
            var status =  dbhlpr.saveddata(note)
            it.hideKeyboard()
            Toast.makeText(this,"data Saved successfully +$status", Toast.LENGTH_SHORT).show()

        }
    }
    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
}