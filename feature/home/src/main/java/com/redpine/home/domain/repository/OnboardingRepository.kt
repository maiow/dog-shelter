package com.redpine.home.domain.repository

import com.redpine.home.domain.model.Onboarding

interface OnboardingRepository {

     fun  getInfo():List<Onboarding>
}