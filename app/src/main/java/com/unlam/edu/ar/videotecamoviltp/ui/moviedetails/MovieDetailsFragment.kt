package com.unlam.edu.ar.videotecamoviltp.ui.moviedetails

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import com.squareup.picasso.Picasso
import com.unlam.edu.ar.videotecamoviltp.R
import com.unlam.edu.ar.videotecamoviltp.databinding.FragmentMovieDetailsBinding
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieDetailsModel
import com.unlam.edu.ar.videotecamoviltp.domain.model.MovieGenreModel
import com.unlam.edu.ar.videotecamoviltp.domain.sharedpreferences.Preferences
import com.unlam.edu.ar.videotecamoviltp.ui.viewmodels.MovieDetailsViewModel
import com.unlam.edu.ar.videotecamoviltp.utils.IMGPathAPI
import org.koin.android.viewmodel.ext.android.viewModel


class MovieDetailsFragment : Fragment() {

    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!
    private val movieDetailsViewModel: MovieDetailsViewModel by viewModel()
    private lateinit var sharedPref: SharedPreferences
    private lateinit var moviePoster: ImageView
    private lateinit var movieTitle: TextView
    private lateinit var movieInfo: TextView
    private lateinit var movieDescription: TextView
    private lateinit var movieGenres: TextView
    private lateinit var favButton: ImageButton
    private lateinit var closeButton: ImageButton

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMovieDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setSharedPref()
        getViews()
        getMovie()
        setListeners()
    }

    private fun setSharedPref() {
        sharedPref = requireContext().getSharedPreferences("FILE_PREFERENCES_USER_ID", Context.MODE_PRIVATE)
    }

    private fun getViews(){
        moviePoster = binding.imgCoverFilm
        movieTitle = binding.tvMovieTitle
        movieInfo = binding.tvMovieData
        movieDescription = binding.tvMovieDescription
        movieGenres = binding.tvMovieGenres
        favButton = binding.ibMovieFav
        closeButton = binding.ibClose
    }

    private fun setListeners(){
        favButton.isEnabled = false
        favButton.setOnClickListener {
            addOrDeleteMovieOfFavList()
        }
        closeButton.setOnClickListener {
            closeMovieDetailsFragment()
        }
    }

    private fun getMovie(){
        movieDetailsViewModel.getMovieDetailsById(getMovieId())
        setUpObservers()
    }

    private fun setUpObservers(){
        movieDetailsViewModel.movieDetailsLiveData.observe(viewLifecycleOwner){ movieData ->
            setMovieData(movieData)
            hideShimmer()
        }

        movieDetailsViewModel.isMovieFavLiveData.observe(viewLifecycleOwner){ isMovieFav ->
            if(isMovieFav){
                isMovieFav()
            }else{
                isNotMovieFav()
            }
            favButton.isEnabled = true
        }
    }

    private fun hideShimmer(){
        binding.llMovieDetailsShimmer.visibility = View.GONE
        binding.clMovieDetails.visibility = View.VISIBLE
    }

    private fun setMovieData(movieData: MovieDetailsModel) {
        movieTitle.text = movieData.title
        movieInfo.text = setMovieInfo(movieData.releaseDate, movieData.runtime)
        movieDescription.text = movieData.description
        movieGenres.text = setGenreList(movieData.genreList)
        setMoviePoster(movieData.poster)
        movieDetailsViewModel.isMovieIntoFavList(getMovieId(),getUserId())
    }

    private fun setMovieInfo(releaseDate: String, runtime: Int): String {
        val year = releaseDate.split("-")[0]
        val runtimeSplit: String = if((runtime/60)>0) "${runtime/60}h ${runtime%60}min" else "${runtime%60}min"

        return "$year    $runtimeSplit"
    }

    private fun setGenreList(genreList: List<MovieGenreModel>): String {
       return genreList.joinToString(separator = " ${String(Character.toChars(0x00B7))} ") { it.genreName }
    }

    private fun setMoviePoster(poster: String) {
        Picasso.get()
            .load("${IMGPathAPI.IMG_API_PATH}${poster}")
            .into(moviePoster)
    }

    private fun addOrDeleteMovieOfFavList(){
        movieDetailsViewModel.addOrDeleteNewMovieFav(getMovieId(), getUserId())
    }

    private fun getUserId():Int{
        return Preferences.getSharedPreferenceUserId(sharedPref)
    }

    private fun getMovieId():Int{
        return requireArguments().getInt("movieId")
    }

    private fun isMovieFav(){
        favButton.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_pressed_fav))
    }

    private fun isNotMovieFav(){
        favButton.setImageDrawable(AppCompatResources.getDrawable(requireContext(), R.drawable.ic_default_fav))
    }

    private fun closeMovieDetailsFragment(){
        parentFragmentManager
            .beginTransaction()
            .remove(this)
            .commit()
    }

}