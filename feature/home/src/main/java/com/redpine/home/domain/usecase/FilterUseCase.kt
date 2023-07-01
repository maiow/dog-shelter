package com.redpine.home.domain.usecase

import com.redpine.home.domain.utils.Filters

interface FilterUseCase {

    fun setFilters(filters: Filters?)

    fun getFilters(): Filters?
}