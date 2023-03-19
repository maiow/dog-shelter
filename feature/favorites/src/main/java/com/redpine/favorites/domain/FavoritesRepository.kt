package com.redpine.favorites.domain

import com.redpine.favorites.domain.model.FavoriteDogs

interface FavoritesRepository {

     fun  getInfo():List<FavoriteDogs>
}