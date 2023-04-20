package com.redpine.core.model

import com.redpine.core.model.card.Dog
import com.redpine.core.model.card.News

class Response(
    var news: News? = null,
    var newsList: List<News>? = null,
    var dogsList: List<Dog>? = null,
    var imagesList: List<String>? = null,
    var exception: Exception? = null
)