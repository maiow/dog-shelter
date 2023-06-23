package com.redpine.home.domain.usecase.impl

import android.content.ContentValues.TAG
import android.util.Log
import com.redpine.core.domain.model.Dog
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.FiltrationRepository
import com.redpine.home.domain.usecase.FilteredDogsUseCase

class FilteredDogsUseCaseImpl(
    private val dogsRepository: DogsRepository,
    private val filtration: FiltrationRepository,
) : FilteredDogsUseCase {

    override suspend fun getFilteredDogs(): List<Dog> {
        val list = dogsRepository.getAllDogs().toMutableList()
        val filters = filtration.getFilters()
        Log.d(TAG, "filterDogs: $filters")
        Log.d(TAG, "filterDogs before: ${list.size}")
        return if (filters != null){
            list.forEach { dog ->
                if(filters.isMale != null && filters.isMale != dog.isMale) list.remove(dog)
                if(filters.color.isNotEmpty() && !filters.color.contains(dog.color)) list.remove(dog)
                if(filters.character.isNotEmpty() && !filters.character.contains(dog.character)) list.remove(dog)
                if(filters.size.isNotEmpty() && !filters.size.contains(dog.size)) list.remove(dog)
            }
            Log.d(TAG, "filterDogs: ${list.size}")
            list
        } else list
    }
}