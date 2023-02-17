package com.unlam.edu.ar.videotecamoviltp.ui.activities

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivitySearchBinding
import com.unlam.edu.ar.videotecamoviltp.ui.adapters.MoviesSearchAdapter
import com.unlam.edu.ar.videotecamoviltp.ui.moviedetails.MovieDetailsFragment
import com.unlam.edu.ar.videotecamoviltp.ui.viewmodels.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SearchActivity : AppCompatActivity() {

    private lateinit var btnHome: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var btnFavourites: ImageButton
    private lateinit var binding: ActivitySearchBinding
    private val vm: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivitySearchBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        getPopularMovies()
        setSearchViewListener()
        setupRecyclerView()
        getViews()
        setListeners()
    }

    private fun getPopularMovies(){
        vm.getPopularMovies()
    }


    @SuppressLint("NotifyDataSetChanged")
    private fun setupRecyclerView() {
        binding.recyclerview.layoutManager = GridLayoutManager(this, 3)
        vm.moviesSearchModelList.observe(this) { movies ->
            val adapter = MoviesSearchAdapter(movies.results) { movieId -> goToMovieDetails(movieId) }
            binding.recyclerview.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }


    private fun setSearchViewListener() {
        binding.searchView.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if(query.isNullOrBlank())
                        vm.getPopularMovies()
                    else
                        vm.getMovie(query.trim())

                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    if(newText.isNullOrBlank())
                        vm.getPopularMovies()
                    else
                        vm.getMovie(newText.trim())

                    return false
                }
            })
    }

    private fun getViews() {
        btnHome = binding.btnHome
        btnSearch = binding.btnSearch
        btnFavourites = binding.btnFavourites
    }

    private fun setListeners() {
        btnHome.setOnClickListener{
            navigateToHome()
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
            .replace(R.id.flSearch, movieDetailsFragment)
            .commit()
    }
}