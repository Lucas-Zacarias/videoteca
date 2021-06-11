package com.unlam.edu.ar.videotecamoviltp

import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivitySearchrecicledBinding
import com.unlam.edu.ar.videotecamoviltp.model.Movies
import com.unlam.edu.ar.videotecamoviltp.service.API
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchActivity : AppCompatActivity() {

    private lateinit var movieAPI: API
    private lateinit var moviesAdapter: MoviesAdapter
    private lateinit var binding: ActivitySearchrecicledBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchrecicledBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        injectDependencies()
        setSearchViewListener()
        setupRecyclerView()
    }
    private fun injectDependencies() {
        movieAPI = API()
        moviesAdapter = MoviesAdapter { movie ->
            Toast.makeText(this@SearchActivity, movie.title, Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.recyclerview.adapter = moviesAdapter

    }

    private fun setSearchViewListener() {
        binding.searchview1.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    onSearch(query)
                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    return false
                }
            })
    }

    private fun onSearch(query: String?) {
        query?.run {
            movieAPI.getMovie(query)
                .enqueue(object : Callback<Movies> {
                    override fun onResponse(call: Call<Movies>, response: Response<Movies>) {
                        if (response.isSuccessful) {
                            val movies = response.body()!!
                            moviesAdapter.updateMovies(movies.results)
                            moviesAdapter.notifyDataSetChanged()
                        } else {
                            showError()
                        }
                    }

                    override fun onFailure(call: Call<Movies>, t: Throwable) {
                        showError()
                    }
                })
        }
    }

    private fun showError() {
        Toast.makeText(this@SearchActivity, R.string.error_movies, Toast.LENGTH_SHORT).show()
    }

}
