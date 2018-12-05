package com.puzzlebench.clean_marvel_kotlin.presentation

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.puzzlebench.clean_marvel_kotlin.R
import com.puzzlebench.clean_marvel_kotlin.data.service.CharacterServicesImpl
import com.puzzlebench.clean_marvel_kotlin.domain.model.Thumbnail
import com.puzzlebench.clean_marvel_kotlin.domain.usecase.GetCharacterByIdServiceUseCase
import com.puzzlebench.clean_marvel_kotlin.presentation.base.BaseRxDialogFragment
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.dismiss
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.getImageByUrl
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.showToast
import com.puzzlebench.clean_marvel_kotlin.presentation.extension.visible
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterDetailPresenter
import com.puzzlebench.clean_marvel_kotlin.presentation.mvp.CharacterDetailsView
import kotlinx.android.synthetic.main.diaglog_fragment_character_detail.*

class CharacterDetailDialogFragment : BaseRxDialogFragment() , CharacterDetailsView {

    companion object {
        const val ARGUMENT_CHARACTER_ID  = "CHARACTER_ID"
        fun newInstance(characterId : String) : CharacterDetailDialogFragment {
            val dialogFragment = CharacterDetailDialogFragment()
            val arguments = Bundle()
            arguments.putString(ARGUMENT_CHARACTER_ID, characterId)
            dialogFragment.arguments = arguments
            return dialogFragment
        }
    }

    val getCharacterByIdServiceUseCase = GetCharacterByIdServiceUseCase(CharacterServicesImpl())

    var presenter = CharacterDetailPresenter(this, getCharacterByIdServiceUseCase, subscriptions)

    /*override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        val builder = AlertDialog.Builder(activity)
        val inflater = activity.layoutInflater
        builder.setView(inflater.inflate(R.layout.diaglog_fragment_character_detail, null))

        return builder.create()
    }*/


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
        return inflater!!.inflate(R.layout.diaglog_fragment_character_detail, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val characterId = arguments.getString(ARGUMENT_CHARACTER_ID)

        if (TextUtils.isEmpty(characterId)) {
            throw IllegalArgumentException("Did you forgot to send the $ARGUMENT_CHARACTER_ID ?")
        }

        presenter.init(characterId)
    }

    override fun showLoader() {
        progressBarCharacterDetail.visible()
        linearContainerCharacterDetail.dismiss()
    }

    override fun showContainer() {
        progressBarCharacterDetail.dismiss()
        linearContainerCharacterDetail.visible()
    }

    override fun showImage(thumbnail: Thumbnail) {
        val string = thumbnail.path + "." + thumbnail.extension
        imageCharacterDetail.getImageByUrl(string)
    }

    override fun showName(name: String) {
        textNameCharacterDetail.text = name
    }

    override fun showDescription(description: String) {
        textDescriptionCharacterDetail.text = description
    }

    override fun hideLoader() {
        progressBarCharacterDetail.dismiss()
    }

    override fun showToastNetworkError(error: String) {
        activity.applicationContext.showToast(error)
    }

}