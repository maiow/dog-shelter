package com.redpine.home.data.repository

import com.redpine.home.domain.repository.FiltrationRepository
import com.redpine.home.domain.utils.Filters

class FiltrationRepositoryImpl: FiltrationRepository {

    private var filters: Filters? = null

    override fun setFilters(filters: Filters?) {
        this.filters = filters
    }

    override fun getFilters(): Filters? {
        return filters
    }
}