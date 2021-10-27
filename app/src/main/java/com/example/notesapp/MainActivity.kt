package com.example.notesapp

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
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
    lateinit var rv:RecyclerView
    lateinit var db:DBHelper


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.button)
        et1 = findViewById(R.id.editTextTextPersonName)
        rv =  findViewById(R.id.rv)
        db =  DBHelper(this)


        Log.d("uuu","${db.retriveData()}")


        btn.setOnClickListener {
            save()
            it.hideKeyboard()
}
              not()
    }


    fun View.hideKeyboard() {
        val inputManager = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputManager.hideSoftInputFromWindow(windowToken, 0)
    }
    fun save(){
        note = et1.text.toString()
        if(note.isNotEmpty()) {
            var status = db.saveddata(note)
            Toast.makeText(this, "data Saved successfully +$status", Toast.LENGTH_SHORT).show()
            et1.text.clear()
        }else{
            Toast.makeText(this, "Type a note", Toast.LENGTH_SHORT).show()
        }
        not()
    }
    fun not(){
        rv.adapter?.notifyDataSetChanged()
        rv.adapter = MyAdapter( db.retriveData(),this)
        rv.layoutManager = LinearLayoutManager(this)
    }
    fun preUpdate(item:Note) {
        var txtt = EditText(this)
        txtt.setText(item.note)
        AlertDialog.Builder(this)
            .setPositiveButton("Update", DialogInterface.OnClickListener { _, _ ->
                update(txtt.text.toString(), item.id)
            })
            .setNegativeButton("No", DialogInterface.OnClickListener { dialog, _ ->
                dialog.cancel()
            })
            .setTitle("Update Note")
            .setView(txtt)
            .create()
            .show()
    }
    fun update(aa:String, bb:Int?){
        var up = db.update(aa,bb)
        Toast.makeText(this,"Updated $up",Toast.LENGTH_SHORT).show()
        not()}
    fun preDelete(item:Int?){
        AlertDialog.Builder(this)
            .setPositiveButton("delete", DialogInterface.OnClickListener{
                    _,_ ->
                if (item != null) {
                    delete(item)
                }
            })
            .setNegativeButton("No", DialogInterface.OnClickListener{
                    dialog,_ -> dialog.cancel()
            })
            .setTitle("Delete Note?")
            .create()
            .show()
    }
    fun delete(aa:Int){
        db = DBHelper(this)
        var up = db.delete(aa)
        Toast.makeText(this,"Deleted $up",Toast.LENGTH_SHORT).show()
        not()


    }


}


