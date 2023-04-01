package com.redpine.core.model.container

import com.redpine.core.model.card.NewDog

data class NewDogContainer(
//    val title: String,
    override val list: List<NewDog>,
): Container