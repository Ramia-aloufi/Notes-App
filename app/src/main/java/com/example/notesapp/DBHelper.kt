package com.example.notesapp

import android.annotation.SuppressLint
import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(context: Context):SQLiteOpenHelper(context,"Notes.db",null,1) {
   private var sdb:SQLiteDatabase = writableDatabase
    override fun onCreate(p0: SQLiteDatabase?) {
        if (p0 != null) {
            p0.execSQL("create table mynote ( id Integer Primary key ,note text)")


        }

    }

    override fun onUpgrade(p0: SQLiteDatabase?, p1: Int, p2: Int) {

    }
    fun saveddata(n1:String): Long {
        val cv = ContentValues()
        cv.put("note",n1)
        var status = sdb.insert("mynote",null,cv)
        return status

    }
    @SuppressLint("Range")
    fun retriveData():ArrayList<String>{
        var al = arrayListOf<String>()
        var c : Cursor = sdb.query("mynote",null,null, null,null,null,null)
        if (c.moveToFirst()) {
            do {
                al.add(c.getString(c.getColumnIndex("note")));
            } while (c.moveToNext());
        }
        return al
    }

    @SuppressLint("Range")
//    fun retriveId(s1:String):Int{
//        var c : Cursor = sdb.query("mynote",null,"id=?", arrayOf(s1),null,null,null)
//        var rr = c.getInt(c.getColumnIndex("id"))
//        return rr
//    }

    fun delete(s1:String):Int{
    var nn =  sdb.delete("mynote","note=?", arrayOf(s1))
        return nn
    }

    fun update(s1:String,s2:String): Int {
        var cv = ContentValues()
        cv.put("note",s1)
        var status = sdb.update("mynote",cv,"note=?", arrayOf(s2))
        return status

    }

}