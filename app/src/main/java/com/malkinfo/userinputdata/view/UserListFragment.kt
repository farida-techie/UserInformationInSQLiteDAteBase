package com.malkinfo.userinputdata.view

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.malkinfo.userinputdata.R
import com.malkinfo.userinputdata.model.HelperSQl
import com.malkinfo.userinputdata.viewmodel.UserAdapter
import kotlinx.android.synthetic.main.fragment_user_list.*


class UserListFragment : Fragment() {

   private lateinit var helperData:HelperSQl
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        /**set Adapter*/
        adpterAttach(view)

        /**set add Button*/
        btnAdd.setOnClickListener {
           val ac = UserListFragmentDirections.addUserFragment()
            Navigation.findNavController(it).navigate(ac)
        }
    }
    fun adpterAttach(v:View){
        helperData = HelperSQl(v.context)
        userLR.layoutManager = LinearLayoutManager(v.context)
        userLR.setHasFixedSize(true)
        val allData = helperData.listUser()
        if (allData.size>0){
            userLR.visibility = View.VISIBLE
            tvU.visibility = View.GONE
            val mAdapter = UserAdapter(v.context,allData)
            userLR.adapter = mAdapter
        }else{
            userLR.visibility = View.GONE
            tvU.visibility = View.VISIBLE
        }

    }



}