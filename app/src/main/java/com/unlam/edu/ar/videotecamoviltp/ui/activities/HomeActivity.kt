package com.unlam.edu.ar.videotecamoviltp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityHomeBinding
import com.unlam.edu.ar.videotecamoviltp.ui.adapters.MoviePosterParentAdapter
import com.unlam.edu.ar.videotecamoviltp.ui.moviedetails.MovieDetailsFragment
import com.unlam.edu.ar.videotecamoviltp.ui.viewmodels.HomeViewModel
import org.koin.android.viewmodel.ext.android.viewModel

class HomeActivity : AppCompatActivity() {

    private lateinit var btnHome: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var btnUser: ImageButton
    private lateinit var btnFavourites: ImageButton
    private lateinit var recyclerView: RecyclerView
    private lateinit var binding: ActivityHomeBinding
    private val vmHome: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityHomeBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        configSwipe()
        setUpRecyclerView()
        getViews()
        setListeners()
        }
    
    private fun configSwipe(){
        binding.swipeHome.setProgressBackgroundColorSchemeResource(R.color.black)
        binding.swipeHome.setColorSchemeColors(getColor(R.color.white))
        binding.swipeHome.setOnRefreshListener {
            binding.rvNestedGenresLoading.visibility = View.VISIBLE
            binding.rvNestedGenres.visibility = View.GONE
            vmHome.getMoviesByGenre()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView() {
        recyclerView = binding.rvNestedGenres
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(
            this,
            LinearLayoutManager.VERTICAL,
            false
        )
        vmHome.getMoviesByGenre()
        vmHome.moviesListByGenre.observe(this) { movies ->

            val adapter = MoviePosterParentAdapter(movies) { movieId -> goToMovieDetails(movieId) }
            recyclerView.adapter = adapter
            adapter.notifyDataSetChanged()

            showData()
        }
    }

    private fun showData() {
        binding.rvNestedGenresLoading.visibility = View.GONE
        binding.rvNestedGenres.visibility = View.VISIBLE
        binding.swipeHome.isRefreshing = false
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

    private fun goToMovieDetails(movieId: Int){
        val bundle = Bundle()
        bundle.putInt("movieId", movieId)

        val movieDetailsFragment = MovieDetailsFragment()
        movieDetailsFragment.arguments = bundle

        supportFragmentManager.beginTransaction()
            .replace(R.id.flHome, movieDetailsFragment)
            .commit()
    }
}