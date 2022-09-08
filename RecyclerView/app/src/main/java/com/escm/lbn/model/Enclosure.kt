package com.escm.lbn.entities

data class Enclosure(
    var link: String,
    var type: String,
    var length: Int,
    var duration: Int,
    var rating: Rating
) {}