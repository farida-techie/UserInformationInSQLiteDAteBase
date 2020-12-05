package com.malkinfo.userinputdata.viewmodel

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.malkinfo.userinputdata.R
import com.malkinfo.userinputdata.model.HelperSQl
import com.malkinfo.userinputdata.model.UserInfo

class UserAdapter(private val c:Context,listU:ArrayList<UserInfo>)
    :RecyclerView.Adapter<UserViewHolder>()
{
    private val listUser :ArrayList<UserInfo>
    private val mArrauList:ArrayList<UserInfo>
    private val mDataBase:HelperSQl
    init {
        this.listUser = listU
        this.mArrauList = listU
        mDataBase = HelperSQl(c)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
       val v = LayoutInflater.from(parent.context)
           .inflate(R.layout.user_item,parent,false)
        return UserViewHolder(v)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
       val user = listUser[position]
        holder.uName.text = user.name
        holder.uMobN.text = user.mobN
        holder.uDate.text = user.data
        if (user.imageU === "null"){
            holder.uImage.setImageResource(R.drawable.ic_camera)
        }else{
            holder.uImage.setImageURI(Uri.parse(user.imageU))
        }
    }

    override fun getItemCount(): Int {
        return listUser.size
    }
}