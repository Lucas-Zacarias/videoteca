package com.unlam.edu.ar.videotecamoviltp.injectDependencies

import android.app.Application
import android.content.Context
import android.widget.Toast
import com.unlam.edu.ar.videotecamoviltp.ImagesAdapter
import com.unlam.edu.ar.videotecamoviltp.MoviesAdapter
import com.unlam.edu.ar.videotecamoviltp.model.GenreID
import com.unlam.edu.ar.videotecamoviltp.model.MovieSearch
import com.unlam.edu.ar.videotecamoviltp.repositories.MovieRepository
import com.unlam.edu.ar.videotecamoviltp.repositories.MoviesRepository
import com.unlam.edu.ar.videotecamoviltp.retrofit.APIImplementation
import com.unlam.edu.ar.videotecamoviltp.retrofit.RetrofitApiService
import com.unlam.edu.ar.videotecamoviltp.ui.HomeViewModel
import com.unlam.edu.ar.videotecamoviltp.ui.SearchViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MoviesApp : Application() {
    val appModule = module {
        single<RetrofitApiService> {APIImplementation()}
        single<MovieRepository> {MoviesRepository(get())}
        viewModel { SearchViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        single { MoviesAdapter { showToast (it, get())}}
        single { ImagesAdapter { showToast(it,get())}}
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesApp)
            modules(appModule)
        }
    }

    private fun showToast(movie: MovieSearch, context: Context) {
        Toast.makeText(context, movie.title, Toast.LENGTH_SHORT).show()
    }
    private fun showToast(movie: GenreID, context: Context) {
        Toast.makeText(context, "Desde el modulo ${movie.id}", Toast.LENGTH_SHORT).show()
    }
}