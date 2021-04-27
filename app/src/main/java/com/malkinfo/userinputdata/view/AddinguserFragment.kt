package com.malkinfo.userinputdata.view

import android.app.Activity.RESULT_OK
import android.content.ContentResolver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.database.sqlite.SQLiteDatabase
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.provider.SyncStateContract.Helpers.insert
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.malkinfo.userinputdata.R
import com.malkinfo.userinputdata.model.DataUser
import com.malkinfo.userinputdata.model.HelperSQl
import com.malkinfo.userinputdata.model.UserInfo
import kotlinx.android.synthetic.main.fragment_addinguser.*


class AddinguserFragment : Fragment() {


    /**Call DataBase*/
    private lateinit var dataHelper:HelperSQl
    var imageUri:Uri? = null
   // private var c :Context? = null

   // var db :SQLiteDatabase? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val rootL =  inflater.inflate(R.layout.fragment_addinguser, container, false)

        return rootL
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        dataHelper = HelperSQl(view.context)


        /**set Image */
        imageUser.setOnClickListener { getCamera(view) }

        submit.setOnClickListener { sendData(it) }
    }

    fun getCamera(v:View){
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE,"imageTitle")
        values.put(MediaStore.Images.Media.DESCRIPTION,"ImageDescription")
        /*put image uri*/
          imageUri = v.context.contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values)!!
        val cameraI = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
         cameraI.putExtra(MediaStore.EXTRA_OUTPUT,imageUri)
        startActivityForResult(cameraI,2)


    }
    fun sendData(v:View){
        val ac = AddinguserFragmentDirections.gotoUserFragment()
        Navigation.findNavController(v).navigate(ac)
        val newUser = UserInfo(
            etName.text.toString(),
            etMb.text.toString(),
            etData.text.toString(),
                ""+imageUri)
        dataHelper.addUserInfo(newUser)
        Toast.makeText(v.context,"Information is submit",Toast.LENGTH_SHORT).show()

    }

    override fun onDestroy() {
        super.onDestroy()
        dataHelper.close()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == RESULT_OK) {
            imageUser.setImageURI(imageUri);
        }
    }


}