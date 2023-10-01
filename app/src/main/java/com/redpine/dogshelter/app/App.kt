package com.redpine.dogshelter.app

import android.app.Application
import com.redpine.dogshelter.di.AppComponent
import com.redpine.dogshelter.di.DaggerAppComponent
import com.redpine.favorites.di.FavoritesDependenciesProvider
import com.redpine.home.di.deps.HomeDependenciesProvider
import com.redpine.profile.di.ProfileDependenciesProvider
import ru.sr.auth.di.deps.AuthDependenciesProvider

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this.applicationContext).build()
        HomeDependenciesProvider.dependencies = appComponent
        FavoritesDependenciesProvider.dependencies = appComponent
        ProfileDependenciesProvider.dependencies = appComponent
        AuthDependenciesProvider.dependencies = appComponent
        //ChatsDependenciesProvider.dependencies = appComponent
    }
}