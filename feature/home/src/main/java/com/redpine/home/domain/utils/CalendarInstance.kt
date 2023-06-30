package com.redpine.home.domain.utils

import java.util.Calendar

class CalendarInstance {

    fun getCurrentYear(): Int{
        return Calendar.getInstance().get(Calendar.YEAR)
    }

    fun getCurrentMonth(): Int{
        return Calendar.getInstance().get(Calendar.MONTH)
    }
}