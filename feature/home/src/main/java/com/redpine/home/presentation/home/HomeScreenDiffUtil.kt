package com.redpine.home.presentation.home

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.redpine.home.domain.model.homeScreen.HomeScreen

class HomeScreenDiffUtil: DiffUtil.ItemCallback<HomeScreen>() {

    override fun areItemsTheSame(oldItem: HomeScreen, newItem: HomeScreen): Boolean = oldItem == newItem

    @SuppressLint("DiffUtilEquals")
    override fun areContentsTheSame(oldItem: HomeScreen, newItem: HomeScreen): Boolean = oldItem.titleId == newItem.titleId


}