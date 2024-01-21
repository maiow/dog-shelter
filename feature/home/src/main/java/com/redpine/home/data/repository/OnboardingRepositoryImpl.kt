package com.redpine.home.data.repository

import com.redpine.home.R
import com.redpine.home.domain.model.Onboarding
import com.redpine.home.domain.repository.OnboardingRepository

class OnboardingRepositoryImpl : OnboardingRepository {

    private val list = listOf<Onboarding>(
        Onboarding(
            R.drawable.start_png_1,
            R.string.find_friend,
            R.string.you_can_choose_pet
        ),
        Onboarding(
            R.drawable.start_png_2,
            R.string.help_easy,
            R.string.payments_any_help
        ),
        Onboarding(
            R.drawable.start_png_3,
            R.string.change_world,
            R.string.become_volunteer
        )
    )

    override fun getInfo() = list
}