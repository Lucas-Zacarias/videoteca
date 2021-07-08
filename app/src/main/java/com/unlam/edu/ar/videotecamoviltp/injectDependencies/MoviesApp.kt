package com.unlam.edu.ar.videotecamoviltp.injectDependencies

import android.app.Application
import com.unlam.edu.ar.videotecamoviltp.*
<<<<<<< HEAD
import com.unlam.edu.ar.videotecamoviltp.data.*
import com.unlam.edu.ar.videotecamoviltp.model.GenreID
import com.unlam.edu.ar.videotecamoviltp.model.MovieSearch
=======
import com.unlam.edu.ar.videotecamoviltp.data.FavDAO
import com.unlam.edu.ar.videotecamoviltp.data.UserDAO
import com.unlam.edu.ar.videotecamoviltp.data.VideotecaDatabase
>>>>>>> Agustin
import com.unlam.edu.ar.videotecamoviltp.repositories.MovieRepository
import com.unlam.edu.ar.videotecamoviltp.repositories.MoviesRepository
import com.unlam.edu.ar.videotecamoviltp.retrofit.APIImplementation
import com.unlam.edu.ar.videotecamoviltp.retrofit.RetrofitApiService
import com.unlam.edu.ar.videotecamoviltp.ui.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class MoviesApp : Application() {
    val appModule = module {
        single<RetrofitApiService> {APIImplementation()}
        single<MovieRepository> {MoviesRepository(get())}
        single { MoviesAdapter }
        single{ UserEntityRepository(get()) }
        single{ FavEntityRepository(get()) }
        single{ MoviesFavAdapter()}
        single<UserDAO>{ VideotecaDatabase.getInstance(get()).userDAO()}
        single<FavDAO>{ VideotecaDatabase.getInstance(get()).favDAO()}
        factory { ImagesAdapter }
        viewModel { FavViewModel(get(),get()) }
        viewModel { SearchViewModel(get()) }
        viewModel { HomeViewModel(get()) }
        viewModel { SignUpViewModel(get()) }
        viewModel { LogInViewModel(get()) }
        viewModel { UserViewModel(get()) }
    }
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@MoviesApp)
            modules(appModule)
        }
    }
}
