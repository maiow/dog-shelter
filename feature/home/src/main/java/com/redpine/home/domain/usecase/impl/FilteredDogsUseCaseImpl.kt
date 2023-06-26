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
        if (filters != null && list.isNotEmpty()) {
            val myiterator: MutableIterator<Dog> = list.iterator()
            while (myiterator.hasNext()) {
                val dog = myiterator.next()
                if ((filters.isMale != null && dog.isMale != filters.isMale) ||
                    (filters.color.isNotEmpty() && !filters.color.contains(dog.color)) ||
                    (filters.character.isNotEmpty() && !filters.character.contains(dog.character)) ||
                    (filters.size.isNotEmpty() && !filters.size.contains(dog.size))
                ) myiterator.remove()
            }
        }
        return list
    }
}