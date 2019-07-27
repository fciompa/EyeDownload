package com.ciompa.cleverlance.ui.main

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.ciompa.cleverlance.R
import com.ciompa.cleverlance.databinding.MainFragmentBinding
import org.koin.android.viewmodel.ext.android.viewModel

class MainView : Fragment() {

    companion object {
        fun newInstance() = MainView()
    }

    private lateinit var binding: MainFragmentBinding
    private val viewModel: MainViewModel by viewModel()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.main_fragment, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.viewModel = viewModel

        viewModel.downloadImageResult.observe(this, Observer {
            val downloadImageResult = it.getContentIfNotHandled()
            if (downloadImageResult != null) {
                Toast.makeText(requireActivity(), downloadImageResult.message, Toast.LENGTH_LONG).show()
            }
        })

        viewModel.hideKeyboard.observe(this, Observer {
            val hideKeyboard = it.getContentIfNotHandled()
            if (hideKeyboard != null) {
                val inputMethodManager =
                    requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

                val windowToken = view!!.applicationWindowToken
                if (windowToken != null) {
                    inputMethodManager.hideSoftInputFromWindow(windowToken, 0)
                }
            }
        })
    }

}
