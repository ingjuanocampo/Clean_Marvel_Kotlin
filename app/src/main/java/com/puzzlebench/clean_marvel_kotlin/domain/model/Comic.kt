package com.puzzlebench.clean_marvel_kotlin.domain.model

data class Comic(var available: Int, var collectionURI : String, var items: ArrayList<Item>) {

    data class Item (var resourceURI: String, var name :String)
}