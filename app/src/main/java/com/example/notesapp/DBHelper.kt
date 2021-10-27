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
    fun retriveData():ArrayList<Note>{
        var al = arrayListOf<Note>()
        var c : Cursor = sdb.query("mynote",null,null, null,null,null,null)
        if (c.moveToFirst()) {
            var id:Int? = null
            var note:String? = null
            do {
                 id = c.getInt(c.getColumnIndex("id"))
                 note =c.getString(c.getColumnIndex("note"))
                al.add(Note(id,note))
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

    fun delete(s1:Int):Int{
    var nn =  sdb.delete("mynote","id=?", arrayOf(s1.toString()))
        return nn
    }

    fun update(s1:String,s2:Int?): Int {
        var cv = ContentValues()
        cv.put("note",s1)
        var status = sdb.update("mynote",cv,"id=?", arrayOf(s2.toString()))
        return status

    }

}