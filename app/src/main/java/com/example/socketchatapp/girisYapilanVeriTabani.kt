package com.example.socketchatapp

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class girisYapilanVeriTabani(mContext:Context):SQLiteOpenHelper(mContext,"girisYapan",null,1) {
    override fun onCreate(db: SQLiteDatabase) {
        db.execSQL("CREATE TABLE girisYapan(girisYapan TEXT);")
        db.execSQL("INSERT INTO girisYapan(girisYapan) VALUES('null')")
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS girisYapan",null)
        onCreate(db)
    }
}