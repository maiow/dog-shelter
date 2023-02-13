package com.redpine.home.data

import com.redpine.home.R
import com.redpine.home.domain.OnboardingRepository
import com.redpine.home.domain.model.Onboarding

class OnboardingRepositoryImpl : OnboardingRepository {

    private val list = listOf<Onboarding>(
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