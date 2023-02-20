package com.unlam.edu.ar.videotecamoviltp.ui.home

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.core.content.ContextCompat.getColor
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.FragmentHomeBinding
import com.unlam.edu.ar.videotecamoviltp.ui.adapters.MoviePosterParentAdapter
import com.unlam.edu.ar.videotecamoviltp.ui.favs.FavFragment
import com.unlam.edu.ar.videotecamoviltp.ui.moviedetails.MovieDetailsFragment
import com.unlam.edu.ar.videotecamoviltp.ui.user.UserFragment
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment: Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var recyclerView: RecyclerView
    private val vmHome: HomeViewModel by viewModel()
    private lateinit var userButton: ImageButton
    private lateinit var searchButton: ImageButton
    private lateinit var favButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configSwipe()
        setUpRecyclerView()
        getViews()
        setListeners()
    }

    private fun configSwipe(){
        binding.swipeHome.setProgressBackgroundColorSchemeResource(R.color.black)
        binding.swipeHome.setColorSchemeColors(getColor(requireContext(), R.color.white))
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
            requireContext(),
            LinearLayoutManager.VERTICAL,
            false
        )
        vmHome.getMoviesByGenre()
        vmHome.moviesListByGenre.observe(viewLifecycleOwner) { movies ->

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
        userButton = binding.ibUser
        searchButton = binding.ibSearchMovies
        favButton = binding.ibFav
    }

    private fun setListeners() {
        userButton.setOnClickListener {
            goToUserAccount()
        }
        searchButton.setOnClickListener {
            goToSearchMovies()
        }
        favButton.setOnClickListener {
            goToFavMovies()
        }
    }

    private fun goToMovieDetails(movieId: Int){
        val bundle = Bundle()
        bundle.putInt("movieId", movieId)

        val movieDetailsFragment = MovieDetailsFragment()
        movieDetailsFragment.arguments = bundle

        childFragmentManager.beginTransaction()
            .replace(R.id.flHome, movieDetailsFragment)
            .commit()
    }

    private fun goToUserAccount(){
        val userFragment = UserFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.flHome, userFragment)
            .commit()
    }

    private fun goToSearchMovies(){
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_homeFragment_to_searchMoviesFragment)
    }

    private fun goToFavMovies(){
        val favFragment = FavFragment()

        childFragmentManager.beginTransaction()
            .replace(R.id.flHome, favFragment)
            .commit()
    }

}