package com.malkinfo.userinputdata.model

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class HelperSQl(c:Context):
    SQLiteOpenHelper(c,DataUser.DATABASE_NAME,null,DataUser.DATABASE_VERSTION),
    DataUser {

    /**ContentValues*/
    lateinit var values:ContentValues
    lateinit var db:SQLiteDatabase
     var curs:Cursor? = null


    /**set SQLite Data*/
    override fun onCreate(db: SQLiteDatabase?) {

        db?.execSQL(DataUser.TABLE_SET)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL(DataUser.TABLE_GET)
        onCreate(db)
    }

    /**set UserInfo*/
    override fun listUser(): ArrayList<UserInfo> {
         db = this.readableDatabase
       val storeUserInfo = ArrayList<UserInfo>()
         curs = db.rawQuery(DataUser.USER_SLECT,null)
        if (curs!!.moveToFirst()){
            do{
                val id = curs!!.getString(0)
                val name = curs!!.getString(1)
                val images = curs!!.getString(2)
                val mobN = curs!!.getString(3)
                val date = curs!!.getString(4)
                storeUserInfo.add(UserInfo(id,name,mobN,images,date))
            }
                while (curs!!.moveToNext())

        }
        curs!!.requery()
        curs!!.close()
        return storeUserInfo
    }

    override fun addUserInfo(us: UserInfo) {
        db = this.writableDatabase
        curs = db.rawQuery(DataUser.USER_SLECT,null)
        values = ContentValues()
        values.put(DataUser.COLUMN_NAME,us.name)
        values.put(DataUser.COLUMN_NO,us.mobN)
        values.put(DataUser.COLUMN_DATE,us.data)
        values.put(DataUser.COLUMN_IMAGE,us.imageU)
        db.insert(DataUser.TABLE_USER,null,values)
        curs!!.requery()

    }

    override fun updataInfo(us: UserInfo) {
        values = ContentValues()
        values.put(DataUser.COLUMN_NAME,us.name)
        values.put(DataUser.COLUMN_NO,us.mobN)
        values.put(DataUser.COLUMN_DATE,us.data)
        values.put(DataUser.COLUMN_IMAGE,us.imageU)
        db = this.writableDatabase
        db.update(DataUser.TABLE_USER,values,"${DataUser.COLUMN_ID} = ?", arrayOf(us.id))
        curs!!.requery()

    }

    override fun deleteInfo(id: Int) {
           db = this.writableDatabase
        db.delete(DataUser.TABLE_USER,"${DataUser.COLUMN_ID}=?", arrayOf(id.toString()))
    }


}