package com.unlam.edu.ar.videotecamoviltp.ui

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.unlam.edu.ar.videotecamoviltp.MoviesAdapter
import com.unlam.edu.ar.videotecamoviltp.databinding.ActivitySearchrecicledBinding
import org.koin.android.ext.android.inject
import org.koin.android.viewmodel.ext.android.viewModel


class SearchActivity : AppCompatActivity() {

    private val moviesAdapter: MoviesAdapter by inject()
    private lateinit var binding: ActivitySearchrecicledBinding
    private val vm: SearchViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySearchrecicledBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        setSearchViewListener()
        setupRecyclerView()
        setupObservers()
    }


    private fun setupRecyclerView() {
        binding.recyclerview.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
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
}
