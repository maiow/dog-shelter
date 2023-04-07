package com.redpine.home.domain.model.homeScreen

import androidx.recyclerview.widget.RecyclerView
import com.redpine.core.model.card.Item

class VerticalGrid(
    override val titleId: Int,
    override val list: List<Item>,
    override val orientation: Int = RecyclerView.VERTICAL,
    override val spanCount:Int = 2
):HomeScreen