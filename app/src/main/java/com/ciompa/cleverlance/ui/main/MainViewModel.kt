package com.ciompa.cleverlance.ui.main

import android.graphics.drawable.BitmapDrawable
import androidx.databinding.Bindable
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ciompa.cleverlance.BR
import com.ciompa.cleverlance.R
import com.ciompa.cleverlance.common.DownloadPictureError
import com.ciompa.cleverlance.domain.Domain
import com.ciompa.cleverlance.ui.Event
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

class MainViewModel(private val domain: Domain) : ViewModel(), Observable, Serializable {

    var formState = MutableLiveData<Event<FormState>>()
    private fun postFormState(value: FormState) = formState.postValue(Event(value))

    var downloadImageResult = MutableLiveData<Event<DownloadImageResult>>()
    private fun postDownloadImageResult(value: DownloadImageResult) = downloadImageResult.postValue(Event(value))

    @Bindable
    var username: String = ""
        set(value) {
            field = value
            loginDataChanged()
            notifyPropertyChanged(BR.username)
        }

    @Bindable
    var password: String = ""
        set(value) {
            field = value
            loginDataChanged()
            notifyPropertyChanged(BR.password)
        }

    @Bindable
    var downloading: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.downloading)
        }

    @Bindable
    var bitmapDrawable: BitmapDrawable? = null
        set(value) {
            field = value
            notifyPropertyChanged(BR.bitmapDrawable)
        }

    @Bindable
    var downloadButtonEnabled: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.downloadButtonEnabled)
        }

    @Bindable
    var downloadedImageVisible: Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.downloadedImageVisible)
        }

    init {
        viewModelScope.launch(Dispatchers.Default) {
            username = domain.getUserLogin()
            password = ""
        }
    }

    fun downloadImage() {
        downloading = true
        viewModelScope.launch(Dispatchers.Default) {
            val result = domain.downloadPicture(username, password)
            val picture = domain.getPicture()
            val message = when (result) {
                DownloadPictureError.Ok -> R.string.download_image_message_ok
                DownloadPictureError.NoInternet -> R.string.download_image_message_no_internet
                DownloadPictureError.Unauthorized -> R.string.download_image_message_unauthorized
                DownloadPictureError.UnknownError -> R.string.download_image_message_unknown_error
            }

            postDownloadImageResult(DownloadImageResult(result, message, picture))

            if (result == DownloadPictureError.Ok) {
                bitmapDrawable = BitmapDrawable(BitmapWorker().decodeSampledBitmapFromResource(picture, 200, 200))
                downloadedImageVisible = true
                downloadButtonEnabled = true
            } else {
                bitmapDrawable = null
                downloadedImageVisible = false
                downloadButtonEnabled = false
            }

            downloading = false
        }
    }


    private fun loginDataChanged() {
        if (!isUserNameValid(username)) {
            postFormState(FormState(R.string.invalid_username, 0))
            downloadButtonEnabled = false
        } else if (!isPasswordValid(password)) {
            postFormState(FormState(0, R.string.invalid_password))
            downloadButtonEnabled = false
        } else {
            postFormState(FormState(0, 0))
            downloadButtonEnabled = true

        }
    }

    private fun isUserNameValid(username: String): Boolean {
        return domain.isUserNameValid(username)
    }

    private fun isPasswordValid(password: String): Boolean {
        return domain.isPasswordValid(password)
    }

    private fun notifyChange() {
        callbacks.notifyCallbacks(this, 0, null)
    }

    private fun notifyPropertyChanged(fieldId: Int) {
        callbacks.notifyCallbacks(this, fieldId, null)
    }

    private val callbacks: PropertyChangeRegistry = PropertyChangeRegistry()

    override fun addOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.add(callback)
    }

    override fun removeOnPropertyChangedCallback(callback: Observable.OnPropertyChangedCallback) {
        callbacks.remove(callback)
    }

}
