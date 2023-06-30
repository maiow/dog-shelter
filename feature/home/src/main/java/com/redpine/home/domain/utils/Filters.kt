package com.redpine.home.domain.utils

data class Filters(
    val isMale: Boolean?,
    val minAge: Int,
    val maxAge: Int,
    val size: List<String>,
    val color: List<String>,
    val character: List<String>,
)