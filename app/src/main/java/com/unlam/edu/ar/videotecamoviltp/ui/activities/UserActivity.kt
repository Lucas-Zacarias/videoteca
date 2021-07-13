package com.unlam.edu.ar.videotecamoviltp.ui.activities

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityUserBinding
import com.unlam.edu.ar.videotecamoviltp.domain.sharedpreferences.Preferences
import com.unlam.edu.ar.videotecamoviltp.ui.viewmodels.UserViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var btnHome: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var btnFav: ImageButton
    private lateinit var btnSignOff: Button
    private lateinit var txtUserName: TextView
    private val userViewModel: UserViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var imgUserIcon: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(LayoutInflater.from(this))
        sharedPref = this.getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
        setContentView(binding.root)
        getViews()
        setListeners()
        setUserName()
    }

    private fun getViews() {
        btnHome = binding.btnHome
        btnSearch = binding.btnSearch
        btnFav = binding.btnFavourites
        btnSignOff = binding.btnCerrarSesion
        txtUserName = binding.txrUserName
        imgUserIcon = binding.imgUserIcon
    }


    private fun setListeners() {
        btnHome.setOnClickListener{
            navigateToHome()
        }
        btnFav.setOnClickListener{
            navigateToFav()
        }
        btnSearch.setOnClickListener{
            navigateToSearch()
        }
        btnSignOff.setOnClickListener{
            signOff()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToFav() {
        val intent = Intent(this, FavActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSearch() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

    private fun setUserName() {
        val userName = userViewModel.getUserNameById(Preferences.getSharedPreferenceUserId(sharedPref))
        if(userName != ""){
            txtUserName.text = userName
        }else{
            txtUserName.text = getString(R.string.nombre_de_usuario_no_encontrado)
        }
    }

    private fun signOff() {
        sharedPref.edit().clear().apply()
        navigateToLogIn()
        finish()
    }
    //borra los datos del shared preferences para que se vuelvan a cargar cuando un nuevo user haga inicio de sesion

    private fun navigateToLogIn() {
        val intent = Intent(this, LogInActivity::class.java)
        startActivity(intent)
    }
}