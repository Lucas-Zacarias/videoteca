package com.unlam.edu.ar.videotecamoviltp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unlam.edu.ar.videotecamoviltp.sharedpreferences.Preferences
import com.unlam.edu.ar.videotecamoviltp.ui.HomeActivity
import com.unlam.edu.ar.videotecamoviltp.ui.LogInActivity

private lateinit var sharedPref: SharedPreferences

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPref = this.getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
        navigateToLogInOrHome()
    }

    private fun navigateToLogInOrHome() {
        if(Preferences.getSharedPreferenceUserId(sharedPref) == 0){
            navigateToLogIn()
        }else{
            navigateToHome()
        }
        finish()
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToLogIn() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }
}