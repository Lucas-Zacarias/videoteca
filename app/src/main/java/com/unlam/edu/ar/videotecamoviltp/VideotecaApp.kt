package com.unlam.edu.ar.videotecamoviltp

import android.app.Application
import com.unlam.edu.ar.videotecamoviltp.data.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.module

class VideotecaApp : Application(){
    val module = module {
        single<UserDAO>{ VideotecaDatabase.getInstance(get()).userDAO()}
        viewModel { SignUpViewModel(get()) }
        viewModel { LogInViewModel(get()) }
        single<FavDAO>{VideotecaDatabase.getInstance(get()).favDAO()}
        viewModel { FavViewModel(get(),get()) }
        single{ MoviesFavAdapter()}
        viewModel { UserViewModel(get()) }
        single{UserEntityRepository(get())}
        single{FavEntityRepository(get())}
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@VideotecaApp)
            modules(module)
        }
    }
}