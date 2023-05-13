package com.redpine.home.presentation.filter

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import androidx.navigation.fragment.findNavController
import com.google.android.material.chip.Chip
import com.redpine.home.HomeBaseFragment
import com.redpine.home.R
import com.redpine.home.databinding.FragmentFilterBinding
import com.redpine.home.presentation.filter.FilterViewModel.Companion.INITIAL_MAX_AGE_ON_SLIDER
import com.redpine.home.presentation.filter.FilterViewModel.Companion.INITIAL_MIN_AGE_ON_SLIDER

class FilterFragment : HomeBaseFragment<FragmentFilterBinding>() {

    override fun initBinding(inflater: LayoutInflater) = FragmentFilterBinding.inflate(inflater)
    private val viewModel: FilterViewModel by lazy { initViewModel() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setCloseButton()
        setAgeSlider()
        setApplyButton()
        setClearButton()
        hideStatusBar()
    }

    private fun hideStatusBar() {
        activity?.window?.decorView?.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN
    }

    private fun setCloseButton() {
        binding.filterCloseBtn.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun setAgeSlider() {
        binding.ageSlider.addOnChangeListener { slider, _, _ ->
            viewModel.minAgeOnSlider = slider.values[0].toInt().toString()
            viewModel.maxAgeOnSlider = slider.values[1].toInt().toString()

            binding.ageNumbersOnSlider.text =
                buildString {
                    append(viewModel.minAgeOnSlider)
                    append("-")
                    append(
                        resources.getQuantityString(
                            R.plurals.age_data,
                            viewModel.maxAgeOnSlider.toInt(),
                            viewModel.maxAgeOnSlider
                        )
                    )
                }
        }
    }

    private fun setApplyButton() {
        binding.applyBtn.setOnClickListener {
            //TODO: отправка собранных данных на сервер

            var i = 0
            while (i <= binding.containerCheckboxes.childCount) {
                val checkView = binding.containerCheckboxes.getChildAt(i)
                if (checkView is CheckBox) {
                    if (checkView.isChecked)
                        viewModel.selectedCheckboxes?.add(checkView.text.toString())
                }
                i++
            }

            viewModel.selectedGenderChip =
                if ((binding.genderChipsGroup.findViewById<Chip>(
                        binding.genderChipsGroup.checkedChipId
                    )) != null
                )
                    binding.genderChipsGroup.findViewById<Chip>(
                        binding.genderChipsGroup.checkedChipId
                    ).text.toString()
                else null


            viewModel.selectedSizeChip =
                if ((binding.sizeChipsGroup.findViewById<Chip>(
                        binding.sizeChipsGroup.checkedChipId
                    )) != null
                )
                    binding.sizeChipsGroup.findViewById<Chip>(
                        binding.sizeChipsGroup.checkedChipId
                    ).text.toString()
                else null

            Log.i("RED", "selected checkboxes = ${viewModel.selectedCheckboxes}")

            Log.i("RED", "genderChipsGroup.checkedChip = ${viewModel.selectedGenderChip}")
            Log.i("RED", "sizeChipsGroup.checkedChip = ${viewModel.selectedSizeChip}")

            Log.i("RED", "minAgeOnSlider = ${viewModel.minAgeOnSlider}")
            Log.i("RED", "maxAgeOnSlider = ${viewModel.maxAgeOnSlider}")

            val filtersText = buildFiltersTextForNextScreen()

            findNavController().navigate(
                FilterFragmentDirections.actionFilterFragmentToDogsFoundFragment(filtersText)
            )
        }
    }

    private fun buildFiltersTextForNextScreen() = buildString {
        append(viewModel.selectedGenderChip ?: getString(R.string.any_gender))
        append(getString(R.string.size_for_found) + " ")
        append(viewModel.selectedSizeChip ?: getString(R.string.any_for_found))
        append(getString(R.string.age_for_found) + " ")
        append(viewModel.minAgeOnSlider)
        append("-")
        append(
            resources.getQuantityString(
                R.plurals.age_data,
                viewModel.maxAgeOnSlider.toInt(),
                viewModel.maxAgeOnSlider
            )
        )
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
            viewModel.selectedCheckboxes = mutableSetOf()
        }
    }
}