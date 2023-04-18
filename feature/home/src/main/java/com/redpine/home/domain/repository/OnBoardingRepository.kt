package com.redpine.home.domain.repository

import com.redpine.home.domain.model.Onboarding

interface OnBoardingRepository {

     fun  getInfo():List<Onboarding>
}