package com.redpine.home.presentation.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.redpine.home.HomeBaseFragment
import com.redpine.home.INITIAL_MAX_AGE_ON_SLIDER
import com.redpine.home.INITIAL_MIN_AGE_ON_SLIDER
import com.redpine.home.databinding.FragmentFilterBinding

class FilterFragment : HomeBaseFragment<FragmentFilterBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFilterBinding.inflate(inflater)
    // private val viewModel: FilterViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // binding.test.text = viewModel.getText()

        setCloseButton()
        setClearButton()


        var minAgeOnSlider = "0"
        var maxAgeOnSlider = "7"

        binding.ageSlider.addOnChangeListener { slider, _, _ ->
            minAgeOnSlider = slider.values[0].toInt().toString()
            maxAgeOnSlider = slider.values[1].toInt().toString()

            //TODO: переписать на placeholder и с изменением год-года-лет
            binding.ageNumbersOnSlider.text =
                buildString {
                    append(minAgeOnSlider)
                    append("-")
                    append(maxAgeOnSlider)
                    append(" лет")
                }
        }

        binding.applyBtn.setOnClickListener {
            //TODO: отправка собранных данных на сервер

            //TODO: собрать чекнутные чекбоксы

            val selectedChipTextGender =
                binding.genderChipsGroup.findViewById<Chip>(binding.genderChipsGroup.checkedChipId).text.toString()

            val selectedChipTextSize =
                binding.sizeChipsGroup.findViewById<Chip>(binding.sizeChipsGroup.checkedChipId).text.toString()

            Log.i("RED", "genderChipsGroup.checkedChip = $selectedChipTextGender")
            Log.i("RED", "sizeChipsGroup.checkedChip = $selectedChipTextSize")

            Log.i("RED", "minAgeOnSlider = $minAgeOnSlider")
            Log.i("RED", "maxAgeOnSlider = $maxAgeOnSlider")

        }


    }

    private fun setCloseButton() {
        binding.filterCloseBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setClearButton() {
        binding.clearBtn.setOnClickListener {
            binding.genderChipsGroup.clearCheck()
            binding.sizeChipsGroup.clearCheck()

            binding.ageSlider.values =
                listOf(INITIAL_MIN_AGE_ON_SLIDER.toFloat(), INITIAL_MAX_AGE_ON_SLIDER.toFloat())

            var i = 0
            while (i <= binding.containerCheckboxes.childCount) {
                val checkView = binding.containerCheckboxes.getChildAt(i)
                if (checkView is CheckBox) checkView.isChecked = false
                i++
            }
        }
    }
}