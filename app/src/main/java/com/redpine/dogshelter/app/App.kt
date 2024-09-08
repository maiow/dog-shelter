package com.redpine.dogshelter.app

import android.app.Application
import com.redpine.auth.di.deps.AuthDependenciesProvider
import com.redpine.dogshelter.di.AppComponent
import com.redpine.dogshelter.di.DaggerAppComponent
import com.redpine.favorites.di.FavoritesDependenciesProvider
import com.redpine.home.di.deps.HomeDependenciesProvider
import com.redpine.profile.di.ProfileDependenciesProvider
import com.vk.id.VKID

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        VKID.init(this)
        //TODO: add token expiration handler
//        VK.addTokenExpiredHandler(tokenTracker)

        appComponent = DaggerAppComponent.builder().application(this.applicationContext).build()
        HomeDependenciesProvider.dependencies = appComponent
        FavoritesDependenciesProvider.dependencies = appComponent
        ProfileDependenciesProvider.dependencies = appComponent
        AuthDependenciesProvider.dependencies = appComponent
        //ChatsDependenciesProvider.dependencies = appComponent
    }

//    private val tokenTracker = object : VKTokenExpiredHandler {
//        override fun onTokenExpired() {
//            Log.d("VK at App class:", "onTokenExpired")
////            WelcomeActivity.startFrom(this@App)
//        }
//    }
}
