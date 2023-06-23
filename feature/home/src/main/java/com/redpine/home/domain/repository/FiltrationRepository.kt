package com.redpine.home.domain.repository

import com.redpine.home.domain.utils.Filters

interface FiltrationRepository {

    fun setFilters(filters: Filters?)

    fun getFilters(): Filters?
}