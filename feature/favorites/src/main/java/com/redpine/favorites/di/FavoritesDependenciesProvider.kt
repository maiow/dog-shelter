package com.redpine.favorites.di

interface FavoritesDependenciesProvider {
    var dependencies:FavoritesDependencies

    companion object: FavoritesDependenciesProvider by FavoritesStore
}