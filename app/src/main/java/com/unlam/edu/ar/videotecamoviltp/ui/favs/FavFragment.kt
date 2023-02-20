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
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.FragmentFavBinding
import com.unlam.edu.ar.videotecamoviltp.domain.sharedpreferences.Preferences
import com.unlam.edu.ar.videotecamoviltp.ui.adapters.MoviesFavAdapter
import com.unlam.edu.ar.videotecamoviltp.ui.moviedetails.MovieDetailsFragment
import org.koin.android.viewmodel.ext.android.viewModel

class FavFragment: Fragment() {
    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    private val favViewModel: FavViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var imgEmptyList: ImageView
    private lateinit var txtEmptyList: TextView
    private lateinit var refresh: ImageButton
    private lateinit var back: ImageButton
    private lateinit var favRV: RecyclerView
    private lateinit var favsExtended: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSharedPreferences()
        getViews()
        setListeners()
        setUpRecyclerView()
    }

    private fun setSharedPreferences(){
        sharedPref = requireContext().getSharedPreferences(
            "FILE_PREFERENCES_USER_ID",
            Context.MODE_PRIVATE)
    }

    private fun getViews(){
        imgEmptyList = binding.imgEmptyList
        txtEmptyList = binding.txtEmptyList
        refresh = binding.ibRefresh
        back = binding.ibClose
        favRV = binding.recyclerview
        favsExtended = binding.ibFavsExtended
    }

    private fun setListeners(){
        refresh.setOnClickListener {
            refreshFavMovies()
        }
        back.setOnClickListener {
            closeFavFragment()
        }
        favsExtended.setOnClickListener {
            goToFavExtendedFragment()
        }
    }

    private fun refreshFavMovies() {
        getMovies()
    }

    private fun closeFavFragment(){
        parentFragmentManager
            .beginTransaction()
            .remove(this)
            .commit()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setUpRecyclerView(){
        favRV.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)

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

    private fun goToMovieDetails(movieId: Int){
        val bundle = Bundle()
        bundle.putInt("movieId", movieId)

        val movieDetailsFragment = MovieDetailsFragment()
        movieDetailsFragment.arguments = bundle

        childFragmentManager.beginTransaction()
            .replace(R.id.flFav, movieDetailsFragment)
            .commit()
    }

    private fun getMovies() {
        favViewModel.getMovieFavsByUserID(getUserId())
        showShimmer()
    }

    private fun getUserId(): Int {
        return Preferences.getSharedPreferenceUserId(sharedPref)
    }

    private fun showShimmer(){
        binding.rvFavMoviesLoading.visibility = View.VISIBLE
        binding.recyclerview.visibility = View.GONE
        binding.divider.visibility = View.GONE
        binding.ivMoreMovieInfo.visibility = View.GONE
        binding.tvMoreMovieInfo.visibility = View.GONE
        binding.ibFavsExtended.visibility = View.GONE
    }

    private fun showImageNotFavMovies(){
        binding.rvFavMoviesLoading.visibility = View.GONE
        imgEmptyList.visibility = View.VISIBLE
        txtEmptyList.visibility = View.VISIBLE
        binding.divider.visibility = View.GONE
        binding.ivMoreMovieInfo.visibility = View.GONE
        binding.tvMoreMovieInfo.visibility = View.GONE
        binding.ibFavsExtended.visibility = View.GONE
    }

    private fun showData(){
        imgEmptyList.visibility = View.GONE
        txtEmptyList.visibility = View.GONE
        binding.rvFavMoviesLoading.visibility = View.GONE
        binding.recyclerview.visibility = View.VISIBLE
        binding.divider.visibility = View.VISIBLE
        binding.ivMoreMovieInfo.visibility = View.VISIBLE
        binding.tvMoreMovieInfo.visibility = View.VISIBLE
        binding.ibFavsExtended.visibility = View.VISIBLE
    }

    private fun goToFavExtendedFragment(){
        Navigation.findNavController(binding.root)
            .navigate(R.id.action_homeFragment_to_favExtendedFragment)
    }
}