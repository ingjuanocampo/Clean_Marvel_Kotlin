package com.puzzlebench.clean_marvel_kotlin.domain.model

class Character(
        val id : String,
        val name: String,
        val description: String,
        val thumbnail: Thumbnail,
        val comics : Comic
)
