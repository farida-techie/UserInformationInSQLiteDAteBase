package com.malkinfo.userinputdata.viewmodel

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.malkinfo.userinputdata.R

class UserViewHolder(itemView:View):RecyclerView.ViewHolder(itemView) {
    val uName :TextView = itemView.findViewById(R.id.tvTitle)
    val uMobN :TextView = itemView.findViewById(R.id.tvSub)
    val uDate :TextView = itemView.findViewById(R.id.tvdate)
    var uImage :ImageView = itemView.findViewById(R.id.reImage)
}