package com.escm.lbn.entities


data class Item(
    var title: String,
    var pubDate: String,
    var link: String,
    var guid: String,
    var author: String,
    var thumbnail: String,
    var description: String,
    var content: String,
    var enclosure: Enclosure,
    var categories: ArrayList<Any>
)