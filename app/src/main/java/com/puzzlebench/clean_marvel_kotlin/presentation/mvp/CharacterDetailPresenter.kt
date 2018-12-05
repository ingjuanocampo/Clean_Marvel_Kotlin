package com.puzzlebench.clean_marvel_kotlin.presentation.mvp

import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterByIdServiceUseCase
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class CharacterDetailPresenter (val view : CharacterDetailsView, private val getCharacterByIdServiceUseCase: GetCharacterByIdServiceUseCase, val subscriptions: CompositeDisposable) {

    fun init (characterId: String) {
        view.showLoader()

        val subscription = getCharacterByIdServiceUseCase.invoke(characterId).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe ({ character ->
                    view.showContainer()
                    view.showImage(character.thumbnail)
                    view.showName(character.name)
                    view.showDescription(character.description)
                    // TODO add more details into the pojo, like series, events etc
                } , { e->
                    view.hideLoader()
                    view.showToastNetworkError(e.message.toString())
                })
        subscriptions.add(subscription)

    }


}