package com.redpine.favorites.data

import com.redpine.favorites.R
import com.redpine.favorites.domain.FavoritesRepository
import com.redpine.favorites.domain.model.FavoriteDogs

class FavoritesRepositoryImpl : FavoritesRepository {

    private val list = listOf<FavoriteDogs>(
        FavoriteDogs(
            R.drawable.dog1,
            "Курт",
            "M",60,2.0f,
//            "Молодой дружелюбный, послушный и очень ласковый пес. Любит дрессировку " +
//                    "за вкусняшки. Имеется лишний вес, нужно будет много с ним гулять, чтобы " +
//                    "похудел."
        ),
        FavoriteDogs(
            R.drawable.dog2,
            "Шафран",
            "M",45,6.0f,
//            "Стеснительный, послушный пушистый рыжий красавец. Любит спокойные прогулки." +
//                    " Идеально брать единственным питомцем в семью без маленьких детей."
        ),
        FavoriteDogs(
            R.drawable.dog3,
            "Эмиль",
            "M",65,1.5f,
//            "Молодой, очень ласковый и послушный пес. Внешне кажется крупным и сильным, " +
//                    "при этом он совсем не доминантен и хорошо ладит с другими собаками. Любит " +
//                    "дрессировку за вкусняшки, но из любви к хозяину дома будет приносить обувь " +
//                    "за просто так"
        ),
    )

    override fun getInfo() = list
}