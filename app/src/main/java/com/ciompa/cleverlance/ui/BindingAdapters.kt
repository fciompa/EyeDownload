package com.ciompa.cleverlance.ui

import android.view.View
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.ciompa.cleverlance.ui.main.MainViewModel

object BindingAdapters {
    @BindingAdapter("visibleGone")
    @JvmStatic
    fun showHide(view: View, show: Boolean) {
        view.visibility = if (show) View.VISIBLE else View.GONE
    }

    @BindingAdapter("showError")
    @JvmStatic
    fun showError(view: EditText, error: Int) {
        view.error = if (error != 0) view.resources.getString(error) else null
    }

    @BindingAdapter("onEditorAction")
    @JvmStatic
    fun onEditorAction(view: EditText, viewModel: MainViewModel) {
        view.setOnEditorActionListener(TextView.OnEditorActionListener { view, actionId, event ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE ->
                    viewModel.downloadImage()
            }

            false
        })
    }
}