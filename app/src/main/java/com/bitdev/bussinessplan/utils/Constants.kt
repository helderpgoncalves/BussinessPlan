package com.bitdev.bussinessplan.utils

import android.content.Context

object Constants {
    const val USER_ID: String = "USER_ID"

    fun getPreferenceUserID(context: Context): String?{
        val sharedPreference = context.getSharedPreferences(USER_ID, Context.MODE_PRIVATE)
        val ID = sharedPreference.getString(USER_ID,"")

        if(ID == "") return null
        return ID
    }

    fun setPreferenceUserID(value: String?, context: Context){
        val sharedPreference = context.getSharedPreferences(USER_ID, Context.MODE_PRIVATE)
        val editor = sharedPreference.edit()

        if(value == null){
            editor.clear()
        }else{
            editor.putString(USER_ID, value)
        }
        editor.apply()
    }
}