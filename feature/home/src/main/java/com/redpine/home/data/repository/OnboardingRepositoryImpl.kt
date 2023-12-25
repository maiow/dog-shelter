package com.redpine.home.data.repository

import com.redpine.home.R
import com.redpine.home.domain.model.Onboarding
import com.redpine.home.domain.repository.OnboardingRepository
import javax.inject.Inject

class OnboardingRepositoryImpl @Inject constructor() : OnboardingRepository {

    private val list = listOf(
        Onboarding(
            R.drawable.start_png_1,
            "Найди себе друга",
            "Ты можешь выбрать питомца по подходящим тебе параметрам"
        ),
        Onboarding(
            R.drawable.start_png_2,
            "Помогать легко",
            "Автоплатежи, передержка, фотопомощь - любой навык может нам помочь"
        ),
        Onboarding(
            R.drawable.start_png_3,
            "Меняй мир с нами",
            "Стань волонтером/опекуном  - целым миром для своего подопечного"
        ),
    )

    override fun getInfo() = list
}