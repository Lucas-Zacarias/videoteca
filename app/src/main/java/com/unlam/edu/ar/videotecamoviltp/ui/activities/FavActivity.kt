package com.unlam.edu.ar.videotecamoviltp.ui.activities

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivityFavBinding
import com.unlam.edu.ar.videotecamoviltp.domain.sharedpreferences.Preferences
import com.unlam.edu.ar.videotecamoviltp.ui.adapters.MoviesFavAdapter
import com.unlam.edu.ar.videotecamoviltp.ui.viewmodels.FavViewModel
import org.koin.android.viewmodel.ext.android.viewModel
class FavActivity : AppCompatActivity() {
    private lateinit var btnHome: ImageButton
    private lateinit var btnSearch: ImageButton
    private lateinit var btnUser: ImageButton
    private lateinit var imgEmptyList: ImageView
    private lateinit var txtEmptyList: TextView
    private lateinit var binding: ActivityFavBinding
    private val favViewModel: FavViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        setSharedPreferences()
        getViews()
        setListeners()
        setUpRecyclerView()
    }

    private fun setSharedPreferences(){
        sharedPref = this.getSharedPreferences(
            "FILE_PREFERENCES_USER_ID",
            Context.MODE_PRIVATE)
    }

    private fun getMovies() {
        favViewModel.getMovieFavsByUserID(getUserId())
        showShimmer()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView() {
        binding.recyclerview.layoutManager = GridLayoutManager(this, 3)

        getMovies()

        favViewModel.moviesFavLiveData.observe(this) { movieList ->
            if(movieList.isEmpty()){
                showImageNotFavMovies()
            }else{
                val adapter = MoviesFavAdapter(movieList)
                binding.recyclerview.adapter = adapter
                adapter.notifyDataSetChanged()

                showData()
            }

        }
    }

    private fun showImageNotFavMovies(){
        binding.rvFavMoviesLoading.visibility = View.GONE
        imgEmptyList.visibility = View.VISIBLE
        txtEmptyList.visibility = View.VISIBLE
    }

    private fun showData(){
        imgEmptyList.visibility = View.GONE
        txtEmptyList.visibility = View.GONE
        binding.rvFavMoviesLoading.visibility = View.GONE
        binding.recyclerview.visibility = View.VISIBLE
    }

    private fun showShimmer(){
        binding.rvFavMoviesLoading.visibility = View.VISIBLE
        binding.recyclerview.visibility = View.GONE
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
}