package com.puzzlebench.clean_marvel_kotlin.data.service.response

import com.puzzlebench.clean_marvel_kotlin.domain.model.Comic

class CharacterResponse (
        val id : String,
        val name: String,
        val description: String,
        val thumbnail: ThumbnailResponse,
        val comics : Comic
)