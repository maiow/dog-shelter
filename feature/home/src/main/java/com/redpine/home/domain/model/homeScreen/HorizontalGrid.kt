package com.redpine.home.domain.model.homeScreen

import androidx.recyclerview.widget.RecyclerView
import com.redpine.core.model.card.Item

data class HorizontalGrid(
    override val titleId: Int,
    override val list: List<Item>,
    override val orientation: Int = RecyclerView.HORIZONTAL,
    override val spanCount:Int = 2
):HomeScreen