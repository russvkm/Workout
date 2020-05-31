package com.russvkm.workout

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.lang.Exception

class DataBaseHelper(context: Context):SQLiteOpenHelper(context,DATABASE_NAME,null,DATABASE_VERSION) {

    companion object{
        const val DATABASE_NAME="HistoryDatabase"
        const val DATABASE_VERSION=1
        const val TABLE_NAME="History"
        const val FIELD_NAME="HISTORY_KEY"
        const val KEY_ID="_ID"
    }
    override fun onCreate(db: SQLiteDatabase?) {
        val createTable="CREATE TABLE $TABLE_NAME ($KEY_ID INTEGER, $FIELD_NAME TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val deleteSql="DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(deleteSql)
        onCreate(db)
    }
    fun pushData(date:String,timeDuration:Int):Long{
        val db=writableDatabase
        val contentValue=ContentValues()
        contentValue.put(FIELD_NAME,date)
        contentValue.put(KEY_ID,timeDuration)
        val success = db.insert(TABLE_NAME, null,contentValue)
        db.close()
        return success
    }

    fun readData():ArrayList<HistoryActivityDataClass>{
        val arrayList=ArrayList<HistoryActivityDataClass>()
        val sql="SELECT * FROM $TABLE_NAME"
        val db=readableDatabase
        val cursor:Cursor
        try{
            cursor=db.rawQuery(sql, null)
        }catch (e:Exception){
            db.execSQL(sql)
            return ArrayList()
        }
        var dateTime:String
        var timeDuration:Int
        if(cursor!= null){
            while (cursor.moveToNext()){
                dateTime=cursor.getString(cursor.getColumnIndex(FIELD_NAME))
                timeDuration=cursor.getInt(cursor.getColumnIndex(KEY_ID))
                val ripeData=HistoryActivityDataClass(timeDuration,dateTime)
                arrayList.add(ripeData)
            }
        }
        return arrayList
    }
}