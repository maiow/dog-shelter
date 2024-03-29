package com.redpine.home.presentation.filter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.core.view.children
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.redpine.core.extensions.onClickToPopBackStack
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentFilterBinding
import com.redpine.home.domain.utils.Filters
import com.redpine.home.presentation.INITIAL_MAX_AGE_ON_SLIDER
import com.redpine.home.presentation.INITIAL_MIN_AGE_ON_SLIDER
import com.redpine.home.presentation.MAX_POSSIBLE_AGE
import com.redpine.home.presentation.MAX_SLIDER_AGE

class FilterFragment : HomeBaseFragment<FragmentFilterBinding>() {

    private val viewModel: FilterViewModel by lazy { initViewModel() }
    override fun initBinding(inflater: LayoutInflater) = FragmentFilterBinding.inflate(inflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCloseButton()
        setAgeFilter()
        setApplyButton()
        setClearButton()
    }

    private fun getGenderFilterValue(): Boolean? {
        return when (binding.genderChipsGroup.checkedChipId) {
            R.id.chip_boy -> true
            R.id.chip_girl -> false
            else -> null
        }
    }

    private fun setAgeFilter() {
        binding.ageSlider.addOnChangeListener { slider, _, _ ->
            val minAge = slider.values[0].toInt().toString()
            val maxAge = slider.values[1].toInt().toString()

            binding.ageNumbersOnSlider.text =
                buildString {
                    append(minAge)
                    append("-")
                    append(
                        resources.getQuantityString(
                            R.plurals.age_data,
                            maxAge.toInt(),
                            maxAge
                        )
                    )
                }
        }
    }

    private fun getAgeSliderValues(): List<Float> = binding.ageSlider.values

    private fun getSizeFilterValues(): List<String> {
        val sizeFilter = mutableListOf<String>()
        binding.sizeChipsGroup.children.forEach { chip ->
            if (chip is Chip && chip.isChecked)
                sizeFilter.add(chip.tag.toString())
        }
        return sizeFilter
    }

    private fun getColorFilterValues(): List<String> {
        val colorFilter = mutableListOf<String>()
        binding.colorFilters.children.forEach {
            if (it is LinearLayout)
                it.children.forEach { checkBox ->
                    if (checkBox is CheckBox && checkBox.isChecked)
                        colorFilter.add(checkBox.tag.toString())
                }
        }
        return colorFilter
    }

    private fun getCharacterFilterValues(): List<String> {
        val characterFilter = mutableListOf<String>()
        binding.characterFilter.children.forEach { checkBox ->
            if (checkBox is CheckBox && checkBox.isChecked)
                characterFilter.add(checkBox.tag.toString())
        }
        return characterFilter
    }

    private fun setCloseButton() = binding.filterCloseBtn.onClickToPopBackStack()

    private fun setApplyButton() {
        binding.applyBtn.setOnClickListener {
            val filters = Filters(
                isMale = getGenderFilterValue(),
                minAge = getAgeSliderValues().first().toInt(),
                maxAge =
                if (getAgeSliderValues().last().toInt() == MAX_SLIDER_AGE) MAX_POSSIBLE_AGE
                else getAgeSliderValues().last().toInt(),
                size = getSizeFilterValues(),
                color = getColorFilterValues(),
                character = getCharacterFilterValues()
            )
            viewModel.onApplyButtonClick(filters)
            if (findNavController().previousBackStackEntry?.destination?.id == R.id.dogsFoundFragment)
                findNavController().popBackStack()
            else
                findNavController().navigate(
                    FilterFragmentDirections.actionFilterFragmentToDogsFoundFragment()
                )
        }
    }

    private fun setClearButton() {
        binding.clearBtn.setOnClickListener {
            binding.genderChipsGroup.clearCheck()
            binding.sizeChipsGroup.clearCheck()

            binding.ageSlider.values =
                listOf(INITIAL_MIN_AGE_ON_SLIDER.toFloat(), INITIAL_MAX_AGE_ON_SLIDER.toFloat())

            binding.characterFilter.children.forEach { checkBox ->
                if (checkBox is CheckBox) checkBox.isChecked = false
            }

            binding.colorFilters.children.forEach {
                if (it is LinearLayout)
                    it.children.forEach { checkBox ->
                        if (checkBox is CheckBox) checkBox.isChecked = false
                    }
            }

            for (i in 0..binding.containerCheckboxes.childCount) {
                val checkView = binding.containerCheckboxes.getChildAt(i)
                if (checkView is CheckBox) checkView.isChecked = false
            }
        }
    }
}