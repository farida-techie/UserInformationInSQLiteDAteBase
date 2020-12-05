package com.malkinfo.userinputdata.model

interface DataUser {

    fun listUser():ArrayList<UserInfo>
    fun addUserInfo(us:UserInfo)
    fun updataInfo(us:UserInfo)
    fun deleteInfo(id:Int)

    companion object{
        const val DATABASE_NAME ="UserInfo"
        const val DATABASE_VERSTION = 1
        const val TABLE_USER = "UserInfo"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME ="UserName"
        const val COLUMN_NO = "UserNumber"
        const val COLUMN_DATE = "Date"
        const val COLUMN_IMAGE = "UserImage"

        /**select data*/
        const val USER_SLECT = "SELECT * FROM $TABLE_USER"

        /**create Table*/
        const val TABLE_GET = "DROP TABLE IF EXISTS $TABLE_USER"

        const val TABLE_SET = "CREATE TABLE $TABLE_USER" +
                "($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAME TEXT ," +
                " $COLUMN_NO TEXT , $COLUMN_DATE TEXT , $COLUMN_IMAGE TEXT)"


    }

}