package com.malkinfo.userinputdata.model

data class UserInfo(
    val id:String,
    val name:String,
    val mobN:String,
    val data:String,
    val imageU:String
    )
{
    internal constructor(
        name:String,
        mobN:String,
        data:String,
        imageU:String) : this("",name, mobN, data, imageU){}
    
    internal constructor(
            name:String,
            mobN:String,
            data:String,
            ) : this("",name, mobN, data, "")
}
