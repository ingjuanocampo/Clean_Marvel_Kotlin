package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail

interface CharacterDetailsView {

    fun showLoader()

    fun showImage(thumbnail: Thumbnail)

    fun showName(name: String)

    fun showDescription(description: String)

    fun hideLoader()

    fun showToastNetworkError(toString: String)

    fun showContainer()

}