package com.unlam.edu.ar.videotecamoviltp.domain.sharedpreferences

import android.content.SharedPreferences

class Preferences {
    companion object{
        fun getSharedPreferenceUserId(sharedPref: SharedPreferences):Int{
            return sharedPref.getInt("userId", 0)
        }
    }
}