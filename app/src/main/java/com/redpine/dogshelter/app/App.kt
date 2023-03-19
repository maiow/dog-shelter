package com.redpine.dogshelter.app

import android.app.Application
import com.redpine.dogshelter.di.AppComponent
import com.redpine.dogshelter.di.DaggerAppComponent
import com.redpine.favorites.di.FavoritesDependenciesProvider
import com.redpine.home.di.HomeDependencies
import com.redpine.home.di.HomeDependenciesProvider

class App:Application() {

   lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.create()
        HomeDependenciesProvider.dependencies = appComponent
        FavoritesDependenciesProvider.dependencies = appComponent
    }
}