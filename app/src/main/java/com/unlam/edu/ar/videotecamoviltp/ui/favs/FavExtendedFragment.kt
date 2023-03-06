package com.unlam.edu.ar.videotecamoviltp.ui.favs

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.FragmentFavExtendedBinding
import com.unlam.edu.ar.videotecamoviltp.domain.sharedpreferences.Preferences
import com.unlam.edu.ar.videotecamoviltp.ui.adapters.MoviesFavAdapter
import com.unlam.edu.ar.videotecamoviltp.ui.moviedetails.MovieDetailsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class FavExtendedFragment: Fragment() {
    private var _binding: FragmentFavExtendedBinding? = null
    private val binding get() = _binding!!
    private lateinit var back: ImageButton
    private lateinit var favRV: RecyclerView
    private lateinit var imgEmptyList: ImageView
    private lateinit var txtEmptyList: TextView
    private val favViewModel: FavViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavExtendedBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSharedPreferences()
        getViews()
        configSwipe()
        setListeners()
        setUpRecyclerView()
    }

    private fun setSharedPreferences(){
        sharedPref = requireContext().getSharedPreferences(
            "FILE_PREFERENCES_USER_ID",
            Context.MODE_PRIVATE)
    }

    private fun getViews(){
        back = binding.ibBack
        favRV = binding.recyclerview
        imgEmptyList = binding.imgEmptyList
        txtEmptyList = binding.txtEmptyList
    }

    private fun configSwipe() {
        binding.swipeRV.setProgressBackgroundColorSchemeResource(R.color.black)
        binding.swipeRV.setColorSchemeColors(
            ContextCompat.getColor(requireContext(), R.color.white))
        binding.swipeRV.setOnRefreshListener {
            getMovies()
        }
    }

    private fun setListeners(){
        back.setOnClickListener {
            goToHome()
        }
    }

    private fun goToHome(){
        Navigation.findNavController(binding.root)
            .navigateUp()
    }

    private fun getMovies(){
        favViewModel.getMovieFavsByUserID(getUserId())
        showShimmer()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView(){
        favRV.layoutManager = GridLayoutManager(requireContext(), 3)

        getMovies()

        favViewModel.moviesFavLiveData.observe(viewLifecycleOwner){ movieList ->
            if(movieList.isEmpty()){
                showImageNotFavMovies()
            }else{
                val adapter = MoviesFavAdapter(movieList) { movieId -> goToMovieDetails(movieId)}
                favRV.adapter = adapter
                adapter.notifyDataSetChanged()

                showData()
            }

        }
    }

    private fun showImageNotFavMovies(){
        binding.rvFavMoviesLoading.visibility = View.GONE
        imgEmptyList.visibility = View.VISIBLE
        txtEmptyList.visibility = View.VISIBLE
        binding.swipeRV.isRefreshing = false
    }

    private fun showData(){
        imgEmptyList.visibility = View.GONE
        txtEmptyList.visibility = View.GONE
        binding.rvFavMoviesLoading.visibility = View.GONE
        binding.recyclerview.visibility = View.VISIBLE
        binding.swipeRV.isRefreshing = false
    }

    private fun showShimmer(){
        binding.rvFavMoviesLoading.visibility = View.VISIBLE
        binding.recyclerview.visibility = View.GONE
    }

    private fun getUserId():Int{
        return Preferences.getSharedPreferenceUserId(sharedPref)
    }

    private fun goToMovieDetails(movieId: Int){
        val bundle = Bundle()
        bundle.putInt("movieId", movieId)

        val movieDetailsFragment = MovieDetailsFragment()
        movieDetailsFragment.arguments = bundle

        childFragmentManager.beginTransaction()
            .replace(R.id.flFavExtended, movieDetailsFragment)
            .commit()
    }

}