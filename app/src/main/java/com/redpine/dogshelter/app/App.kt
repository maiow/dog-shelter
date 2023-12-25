package com.redpine.dogshelter.app

import android.app.Application
import com.redpine.chats.di.ChatsDependenciesProvider
import com.redpine.core.component.RedPineApp
import com.redpine.dogshelter.di.AppComponent
import com.redpine.dogshelter.di.DaggerAppComponent
import com.redpine.favorites.di.FavoritesDependenciesProvider

class App : RedPineApp,Application() {

   override lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this.applicationContext).build()
        FavoritesDependenciesProvider.dependencies = appComponent
        ChatsDependenciesProvider.dependencies = appComponent
    }
}