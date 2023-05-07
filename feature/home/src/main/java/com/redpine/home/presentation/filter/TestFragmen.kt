package com.redpine.home.presentation.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import com.redpine.home.HomeBaseFragment
import com.redpine.home.databinding.ItemCheckGroupBinding

class TestFragment:HomeBaseFragment<ItemCheckGroupBinding> (){
    override fun initBinding(inflater: LayoutInflater)= ItemCheckGroupBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)




        binding.button.setOnClickListener {
            binding.test.checkedChipIds.forEach{ it->
                Log.e("Kart","$it")
            }
        }
    }
}