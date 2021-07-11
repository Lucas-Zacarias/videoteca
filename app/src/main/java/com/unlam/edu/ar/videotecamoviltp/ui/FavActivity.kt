package com.unlam.edu.ar.videotecamoviltp.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.appcompat.app.AppCompatActivity
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityFavBinding
import com.unlam.edu.ar.videotecamoviltp.sharedpreferences.Preferences
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel

class FavActivity : AppCompatActivity() {
    private lateinit var btnHome: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var btnUser: ImageButton
    private lateinit var imgEmptyList: ImageView
    private lateinit var txtEmptyList: TextView
    private lateinit var binding: ActivityFavBinding
    private val favViewModel: FavViewModel by viewModel()
    private val moviesFavAdapter: MoviesFavAdapter by inject()
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(LayoutInflater.from(this))
        sharedPref = this.getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
        setContentView(binding.root)
        getViews()
        setListeners()
        setUpRecyclerView()
        getMovies()
    }

    private fun getMovies() {
        if (getMovieIdList().isNotEmpty()) {
            imgEmptyList.visibility = View.GONE
            txtEmptyList.visibility = View.GONE
        } else {
            imgEmptyList.visibility = View.VISIBLE
            txtEmptyList.visibility = View.VISIBLE
        }
        favViewModel.updateMoviesLiveData(getMovieIdList())
        setUpObserver()

    }

    private fun setUpObserver() {
        favViewModel.moviesFavLiveData.observe(this, {
            moviesFavAdapter.updateMovies(it)
            moviesFavAdapter.notifyDataSetChanged()
        })
    }

    private fun setUpRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false)
        binding.recyclerview.adapter = moviesFavAdapter
    }

    private fun getViews() {
        btnHome = binding.btnHome
        btnSearch = binding.btnSearch
        btnUser = binding.btnUser
        imgEmptyList = binding.imgEmptyList
        txtEmptyList = binding.txtEmptyList
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

    private fun getUserId():Int{
        return Preferences.getSharedPreferenceUserId(sharedPref)
    }

    private fun getMovieIdList(): List<Int> {
        return favViewModel.getMoviesFavIDListByUserID(getUserId())
    }
}