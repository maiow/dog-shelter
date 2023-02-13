package com.redpine.home.domain

import com.redpine.home.domain.model.Onboarding

interface OnboardingRepository {

     fun  getInfo():List<Onboarding>
}