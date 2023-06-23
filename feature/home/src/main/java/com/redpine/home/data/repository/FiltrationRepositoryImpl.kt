package com.redpine.home.data.repository

import android.content.ContentValues.TAG
import android.util.Log
import com.redpine.home.domain.repository.FiltrationRepository
import com.redpine.home.domain.utils.Filters

class FiltrationRepositoryImpl: FiltrationRepository {

    private var filters: Filters? = null

    init {
        Log.d(TAG, "repo: ")
    }

    override fun setFilters(filters: Filters?) {
        this.filters = filters
        Log.d(TAG, "setFilters: ${this.filters}")
    }

    override fun getFilters(): Filters? {
        return filters
    }
}