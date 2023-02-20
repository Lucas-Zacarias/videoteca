package com.unlam.edu.ar.videotecamoviltp.ui.searchmovies

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.FragmentSearchMoviesBinding
import com.unlam.edu.ar.videotecamoviltp.ui.adapters.MoviesSearchAdapter
import com.unlam.edu.ar.videotecamoviltp.ui.moviedetails.MovieDetailsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class SearchMoviesFragment: Fragment() {
    private var _binding: FragmentSearchMoviesBinding? = null
    private val binding get() = _binding!!
    private val searchMoviesViewModel: SearchViewModel by viewModel()
    private lateinit var back: ImageButton
    private lateinit var searchMoviesSV: SearchView
    private lateinit var searchMoviesRV: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchMoviesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getViews()
        getPopularMovies()
        setListeners()
        setUpRecyclerView()
    }

    private fun getViews(){
        back = binding.ibBack
        searchMoviesSV = binding.svMovies
        searchMoviesRV = binding.recyclerview
    }

    private fun getPopularMovies(){
        searchMoviesViewModel.getPopularMovies()
    }

    private fun setListeners() {
        setBackButton()
        setSearchViewListener()
    }

    private fun setBackButton(){
        back.setOnClickListener {
            backToHome()
        }
    }

    private fun backToHome() {
        val navOptions = NavOptions.Builder().setPopUpTo(R.id.searchMoviesFragment, true).build()
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_searchMoviesFragment_to_homeFragment, null, navOptions)
    }

    private fun setSearchViewListener(){
        searchMoviesSV.onActionViewExpanded()
        searchMoviesSV.requestFocus()
        searchMoviesSV.setOnQueryTextListener(
            object : SearchView.OnQueryTextListener {
                override fun onQueryTextSubmit(query: String?): Boolean {
                    if (query.isNullOrBlank())
                        searchMoviesViewModel.getPopularMovies()
                    else
                        searchMoviesViewModel.getMovie(query.trim())

                    return true
                }

                override fun onQueryTextChange(newText: String?): Boolean {
                    if (newText.isNullOrBlank())
                        searchMoviesViewModel.getPopularMovies()
                    else
                        searchMoviesViewModel.getMovie(newText.trim())

                    return false
                }
            })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView(){
        searchMoviesRV.layoutManager = GridLayoutManager(requireContext(), 3)

        searchMoviesViewModel.moviesSearchModelList.observe(viewLifecycleOwner) { movies ->
            val adapter = MoviesSearchAdapter(movies.results) { movieId -> goToMovieDetails(movieId)}
            searchMoviesRV.adapter = adapter
            adapter.notifyDataSetChanged()
        }
    }

    private fun goToMovieDetails(movieId: Int) {
        val bundle = Bundle()
        bundle.putInt("movieId", movieId)

        val movieDetailsFragment = MovieDetailsFragment()
        movieDetailsFragment.arguments = bundle

        childFragmentManager.beginTransaction()
            .replace(R.id.flSearch, movieDetailsFragment)
            .commit()
    }
}