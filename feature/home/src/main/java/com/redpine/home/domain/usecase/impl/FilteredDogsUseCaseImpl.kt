package com.redpine.home.domain.usecase.impl

import com.redpine.core.domain.model.Dog
import com.redpine.home.domain.repository.DogsRepository
import com.redpine.home.domain.repository.FiltrationRepository
import com.redpine.home.domain.usecase.FilteredDogsUseCase
import com.redpine.home.domain.utils.CalendarInstance

class FilteredDogsUseCaseImpl(
    private val dogsRepository: DogsRepository,
    private val filtration: FiltrationRepository,
    private val calendarInstance: CalendarInstance,
) : FilteredDogsUseCase {

    override suspend fun getFilteredDogs(): List<Dog> {
        val list = dogsRepository.getAllDogs().toMutableList()
        val filters = filtration.getFilters()
        if (filters != null && list.isNotEmpty()) {
            val myIterator: MutableIterator<Dog> = list.iterator()
            while (myIterator.hasNext()) {
                val dog = myIterator.next()
                if ((filters.isMale != null && dog.isMale != filters.isMale) ||
                    (filters.color.isNotEmpty() && !filters.color.contains(dog.color)) ||
                    (filters.character.isNotEmpty() && !filters.character.contains(dog.character)) ||
                    (filters.size.isNotEmpty() && !filters.size.contains(dog.size)) ||
                    (getDogAge(dog) < filters.minAge * 12) || (getDogAge(dog) > filters.maxAge * 12)
                ) myIterator.remove()
            }
        }
        return list
    }

    private fun getDogAge(dog: Dog): Int {
        val years = calendarInstance.getCurrentYear() - dog.age.takeLast(4).toInt()
        val months = calendarInstance.getCurrentMonth() - dog.age.take(2).toInt()
        return years * 12 + months
    }
}