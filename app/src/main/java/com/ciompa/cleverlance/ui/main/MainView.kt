package com.ciompa.cleverlance.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
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

        viewModel.formState.observe(this, Observer {
            val formState = it.getContentIfNotHandled()
            if (formState != null) {
                when {
                    formState.passwordError != null && formState.passwordError != 0 ->
                        Toast.makeText(requireActivity(), formState.passwordError, Toast.LENGTH_LONG).show()

                    formState.usernameError != null && formState.usernameError != 0 ->
                        Toast.makeText(requireActivity(), formState.usernameError, Toast.LENGTH_LONG).show()
                }
            }
        })

        viewModel.downloadImageResult.observe(this, Observer {
            val downloadImageResult = it.getContentIfNotHandled()
            if (downloadImageResult != null) {
                Toast.makeText(requireActivity(), downloadImageResult.toString(), Toast.LENGTH_LONG).show()
            }
        })
    }

}
