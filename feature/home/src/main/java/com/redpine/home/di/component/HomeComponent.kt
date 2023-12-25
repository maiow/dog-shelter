package com.redpine.home.di.component

import com.redpine.core.component.RedPineComponent
import com.redpine.home.presentation.authorization.auth.AuthFragment
import com.redpine.home.presentation.authorization.registration.RegistrationFragment
import com.redpine.home.presentation.authorization.resetpassword.ResetPasswordFragment
import com.redpine.home.presentation.filter.FilterFragment
import com.redpine.home.presentation.found.DogsFoundFragment
import com.redpine.home.presentation.home.HomeFragment
import com.redpine.home.presentation.news.NewsListFragment
import com.redpine.home.presentation.news.SingleNewsFragment
import com.redpine.home.presentation.onboarding.OnboardingFragment
import com.redpine.home.presentation.pets_card.PetsCardFragment

interface HomeComponent:RedPineComponent{
    fun inject(fragment:AuthFragment)
    fun inject(fragment:RegistrationFragment)
    fun inject(fragment:ResetPasswordFragment)
    fun inject(fragment:FilterFragment)
    fun inject(fragment:DogsFoundFragment)
    fun inject(fragment:NewsListFragment)
    fun inject(fragment:SingleNewsFragment)
    fun inject(fragment:HomeFragment)
    fun inject(fragment:OnboardingFragment)
    fun inject(fragment:PetsCardFragment)
}