package com.redpine.core.model.card

data class RecentSeenDog(
    override val id: Int,
    override val name: String,
    override val age: String,
    override val isFavorite: Boolean,
    override val isNew: Boolean
): Dog
