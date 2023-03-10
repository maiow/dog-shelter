package com.redpine.favorites.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import com.redpine.favorites.FavoritesBaseFragment
import com.redpine.favorites.databinding.FragmentFavoritesBinding

class FavoritesFragment : FavoritesBaseFragment<FragmentFavoritesBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFavoritesBinding.inflate(inflater)
    private val viewModel: FavoritesViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.test.text = viewModel.getText()
        binding.testImage.setImageResource(viewModel.getDogInfo().resImgId)
    }
}