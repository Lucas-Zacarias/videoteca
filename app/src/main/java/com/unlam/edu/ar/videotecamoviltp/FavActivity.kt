package com.unlam.edu.ar.videotecamoviltp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityFavBinding

class FavActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
    }
}