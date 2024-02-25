package com.redpine.dogshelter.app

import android.app.Application
import com.redpine.auth.di.deps.AuthDependenciesProvider
import com.redpine.chats.di.ChatsDependenciesProvider
import com.redpine.dogshelter.BuildConfig
import com.redpine.dogshelter.di.AppComponent
import com.redpine.dogshelter.di.DaggerAppComponent
import com.redpine.favorites.di.FavoritesDependenciesProvider
import com.redpine.home.di.deps.HomeDependenciesProvider
import com.redpine.profile.di.ProfileDependenciesProvider
import io.getstream.chat.android.client.ChatClient
import io.getstream.chat.android.client.logger.ChatLogLevel
import io.getstream.chat.android.offline.model.message.attachments.UploadAttachmentsNetworkType
import io.getstream.chat.android.offline.plugin.configuration.Config
import io.getstream.chat.android.offline.plugin.factory.StreamOfflinePluginFactory

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder().application(this.applicationContext).build()
        HomeDependenciesProvider.dependencies = appComponent
        FavoritesDependenciesProvider.dependencies = appComponent
        ProfileDependenciesProvider.dependencies = appComponent
        AuthDependenciesProvider.dependencies = appComponent
        ChatsDependenciesProvider.dependencies = appComponent


        // Step 1 - Set up the OfflinePlugin for offline storage
        val offlinePluginFactory = StreamOfflinePluginFactory(
            config = Config(
                // Enables the background sync which is performed to sync user actions done without the Internet connection.
                backgroundSyncEnabled = true,
                // Enables the ability to receive information about user activity such as last active date and if they are online right now.
                userPresence = true,
                // Enables using the database as an internal caching mechanism.
                persistenceEnabled = true,
                // An enumeration of various network types used as a constraint inside upload attachments worker.
                uploadAttachmentsNetworkType = UploadAttachmentsNetworkType.NOT_ROAMING,
                // Whether the SDK will use a new sequential event handling mechanism.
                useSequentialEventHandler = false,
            ),
            appContext = applicationContext,
        )

        // Step 2 - Set up the client for API calls with the plugin for offline storage
        ChatClient.Builder(BuildConfig.API_KEY, applicationContext)
            .withPlugin(offlinePluginFactory)
            .logLevel(ChatLogLevel.ALL) // Set to NOTHING in prod
            .build()
    }
}
