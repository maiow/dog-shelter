package com.redpine.home.domain.usecase.impl

import com.redpine.home.domain.repository.FiltrationRepository
import com.redpine.home.domain.usecase.FilterUseCase
import com.redpine.home.domain.utils.Filters

class FilterUseCaseImpl(private val filtration: FiltrationRepository) : FilterUseCase {

    override fun setFilters(filters: Filters?) {
        filtration.setFilters(filters)
    }
}