package com.redpine.home.presentation.filter

import com.redpine.core.base.BaseViewModel
import com.redpine.home.domain.usecase.FilterUseCase
import com.redpine.home.domain.utils.Filters
import javax.inject.Inject

class FilterViewModel @Inject constructor(
    private val filterUseCase: FilterUseCase
) : BaseViewModel() {

    fun onApplyButtonClick(filters: Filters) = filterUseCase.setFilters(filters)
}