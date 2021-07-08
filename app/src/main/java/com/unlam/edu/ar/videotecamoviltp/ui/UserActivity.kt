package com.unlam.edu.ar.videotecamoviltp.ui

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityUserBinding

class UserActivity : AppCompatActivity() {
    private lateinit var binding: ActivityUserBinding
    private lateinit var btnHome: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var btnFav: ImageButton
    private lateinit var btnSignOff: Button
    private lateinit var txtUserName: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        getViews()
        setListeners()
    }

    private fun getViews() {
        btnHome = binding.btnHome
        btnSearch = binding.btnSearch
        btnFav = binding.btnFavourites
        btnSignOff = binding.btnCerrarSesion
        txtUserName = binding.txrUserName
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
}