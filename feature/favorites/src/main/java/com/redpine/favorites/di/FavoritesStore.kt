package com.redpine.favorites.di

import kotlin.properties.Delegates

object FavoritesStore : FavoritesDependenciesProvider {
    override var dependencies: FavoritesDependencies by Delegates.notNull()
}