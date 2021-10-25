package com.example.notesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"Note.db",null,1) {
   private var sdb:SQLiteDatabase = writableDatabase
    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL("create table student (note text)")


        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun saveddata(n1:String): Long {
        val cv = ContentValues()
        cv.put("note",n1)
        var status = sdb.insert("student",null,cv)
        return status

    }
    @SuppressLint("Range")
    fun retriveData():ArrayList<String>{
        var al = arrayListOf<String>()
        var c : Cursor = sdb.query("student",null,null, null,null,null,null)
        if (c.moveToFirst()) {
            do {
                al.add(c.getString(c.getColumnIndex("note")));
            } while (c.moveToNext());
        }
        return al
    }

}