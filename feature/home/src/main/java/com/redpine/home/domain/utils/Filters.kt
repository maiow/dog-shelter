package com.redpine.home.domain.utils

import javax.inject.Singleton

data class Filters(
    val isMale: Boolean?,
    val minAge: Long,
    val maxAge: Long,
    val size: List<String>,
    val color: List<String>,
    val character: List<String>,
)