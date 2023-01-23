package com.unlam.edu.ar.videotecamoviltp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityHomeBinding
import com.unlam.edu.ar.videotecamoviltp.ui.viewmodels.HomeViewModel
import com.unlam.edu.ar.videotecamoviltp.ui.adapters.MoviePosterAdapter
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var btnHome: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var btnUser: ImageButton
    private lateinit var btnFavourites: ImageButton
    private lateinit var moviePosterAdapterAccion: MoviePosterAdapter
    private lateinit var imagesAdapterDrama: MoviePosterAdapter
    private lateinit var moviePosterAdapterComedia: MoviePosterAdapter
    private lateinit var imagesAdapterTerror: MoviePosterAdapter
    private lateinit var binding: ActivityHomeBinding
    private val vmHome: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setupRecyclerView()
        setupImagesDrama()
        setupImagesAccion()
        setupImagesComedia()
        setupImagesTerror()
        getViews()
        setListeners()
        }

    private fun setupRecyclerView() {
        binding.recyclerview1.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        moviePosterAdapterAccion = MoviePosterAdapter()
        binding.recyclerview1.adapter = moviePosterAdapterAccion
        binding.recyclerview2.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        moviePosterAdapterComedia = MoviePosterAdapter()
        binding.recyclerview2.adapter = moviePosterAdapterComedia
        binding.recyclerview3.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        imagesAdapterTerror = MoviePosterAdapter()
        binding.recyclerview3.adapter = imagesAdapterTerror
        binding.recyclerview4.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        imagesAdapterDrama = MoviePosterAdapter()
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
            moviePosterAdapterAccion.updateMovies(it.results)
            moviePosterAdapterAccion.notifyDataSetChanged()
        })
    }
    private fun setupObserversComedia(){
        vmHome.moviesListDataComedia.observe(this,{
            moviePosterAdapterComedia.updateMovies(it.results)
            moviePosterAdapterComedia.notifyDataSetChanged()
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

    private fun getViews() {
        btnHome = binding.btnHome
        btnSearch = binding.btnSearch
        btnUser = binding.btnUser
        btnFavourites = binding.btnFavourites
    }

    private fun setListeners() {
        btnHome.setOnClickListener{
            navigateToHome()
        }
        btnUser.setOnClickListener{
            navigateToUser()
        }
        btnSearch.setOnClickListener{
            navigateToSearch()
        }
        btnFavourites.setOnClickListener{
            navigateToFav()
        }
    }

    private fun navigateToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToUser() {
        val intent = Intent(this, UserActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSearch() {
        val intent = Intent(this, SearchActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToFav() {
        val intent = Intent(this, FavActivity::class.java)
        startActivity(intent)
    }
}