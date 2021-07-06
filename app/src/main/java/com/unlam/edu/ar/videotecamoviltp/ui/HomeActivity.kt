package com.unlam.edu.ar.videotecamoviltp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlam.edu.ar.videotecamoviltp.ImagesAdapter
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityHomeBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private val imagesAdapterAccion: ImagesAdapter by inject()
    private val imagesAdapterDrama: ImagesAdapter by inject()
    private val imagesAdapterTerror: ImagesAdapter by inject()
    private val imagesAdapterComedia: ImagesAdapter by inject()
    private lateinit var binding: ActivityHomeBinding
    private val vmHome: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setupRecyclerView()
        setupImagesDrama()
        }

    private fun setupRecyclerView() {
        binding.recyclerview1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview1.adapter = imagesAdapterAccion
        binding.recyclerview2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview2.adapter = imagesAdapterComedia
        binding.recyclerview3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview3.adapter = imagesAdapterTerror
        binding.recyclerview4.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        binding.recyclerview4.adapter = imagesAdapterDrama
    }

    private fun setupObserversDrama(){
        vmHome.moviesListDataDrama.observe(this,{
            imagesAdapterDrama.updateMovies(it.results)
            imagesAdapterDrama.notifyDataSetChanged()
        })
    }
   private fun setupObserversAccion(){
        vmHome.moviesListDataAccion.observe(this,{
            imagesAdapterAccion.updateMovies(it.results)
            imagesAdapterAccion.notifyDataSetChanged()
        })
    }
    private fun setupObserversComedia(){
        vmHome.moviesListDataComedia.observe(this,{
            imagesAdapterComedia.updateMovies(it.results)
            imagesAdapterComedia.notifyDataSetChanged()
        })
    }
    private fun setupObserversTerror(){
        vmHome.moviesListDataTerror.observe(this,{
            imagesAdapterTerror.updateMovies(it.results)
            imagesAdapterTerror.notifyDataSetChanged()
        })
    }
    private fun setupImagesDrama(genre_id: Int = 18){
        genre_id.run {
            vmHome.getMovieDrama(this)
            setupObserversDrama()
        }
    }
    private fun setupImagesAccion(genre_id: Int = 28){
        genre_id.run {
            vmHome.getMovieAction(this)
            setupObserversAccion()
        }
    }
    private fun setupImagesTerror(genre_id: Int = 27){
        genre_id.run {
            vmHome.getMovieTerror(this)
            setupObserversTerror()
        }
    }
    private fun setupImagesComedia(genre_id: Int = 35){
        genre_id.run {
            vmHome.getMovieComedia(this)
            setupObserversComedia()
        }
    }

}



