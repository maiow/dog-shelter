package com.redpine.favorites.domain.model

class FavoriteDogs(
    val resImgId: Int,
    val name: String,
    val sex: String,
    val height: Int,
    val age: Float
    //Float может быть нужен для 1,5 лет или щенков <1 года
    //Но на слайдере на экране фильтров точно не будет месяцев, там возраст от 0 до 12 лет
)