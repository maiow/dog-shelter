package com.redpine.favorites.di

import com.redpine.core.base.ComponentViewModel

class FavoritesComponentViewModel: ComponentViewModel() {

   override val moduleComponent = DaggerFavoritesComponent
        .builder()
        .dependencies(FavoritesDependenciesProvider.dependencies)
        .build()

}
