package com.example.proyector6

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.File


class SQLiteGestor(context: SecondFragment, name: String) : SQLiteOpenHelper(context, name,null,1) {
    private val myContext: Context? = null

    init {

        val PATH_BD = "/data/data/" + context.packageName + "/databases"
        val NOM_BD = "proyector6.sqlite"
        val dir = File(PATH_BD)
        if (!dir.exists())
            dir.mkdir()
        val f = File(dir, NOM_BD)
        if (!f.exists()) {
            val f_in = context.resources.openRawResource(R.raw.proyector6)
            f.writeBytes(f_in.readBytes())
        }
    }

    override fun onCreate(sqLiteDatabase: SQLiteDatabase) {}

    override fun onUpgrade(sqLiteDatabase: SQLiteDatabase, i: Int, i2: Int) {}
}