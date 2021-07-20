package com.unlam.edu.ar.videotecamoviltp.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlam.edu.ar.videotecamoviltp.ui.adapters.MoviesAdapter
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivitySearchrecicledBinding
import com.unlam.edu.ar.videotecamoviltp.ui.viewmodels.SearchViewModel
import org.koin.android.viewmodel.ext.android.viewModel


class SearchActivity : AppCompatActivity() {

    private lateinit var btnHome: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var btnUser: ImageButton
    private lateinit var btnFavourites: ImageButton
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var binding: ActivitySearchrecicledBinding
    private val vm: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchrecicledBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setSearchViewListener()
        setupRecyclerView()
        setupObservers()
        getViews()
        setListeners()
    }


    private fun setupRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        moviesAdapter = MoviesAdapter()
        binding.recyclerview.adapter = moviesAdapter
    }

    private fun setupObservers(){
        vm.moviesList.observe(this, Observer {
            moviesAdapter.updateMovies(it.results)
            moviesAdapter.notifyDataSetChanged()
        })
    }
    private fun setSearchViewListener() {
        binding.searchview1.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    query?.run {
                        vm.getMovie(this)
                    }
                    return true
                }
                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
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