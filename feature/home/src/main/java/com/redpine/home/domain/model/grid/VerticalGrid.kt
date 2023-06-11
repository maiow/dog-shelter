package com.redpine.home.domain.model.grid

import androidx.recyclerview.widget.RecyclerView
import com.redpine.core.domain.model.Item

data class VerticalGrid(
    override val titleId: Int,
    override var list: List<Item>,
    override val orientation: Int = RecyclerView.VERTICAL,
    override val spanCount: Int = 2
) : Grid