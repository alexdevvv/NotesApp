package com.example.notesapp.presentation.registration.registration_screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentRegistrationScreenBinding
import com.example.notesapp.domain.model.ModelSendDataOnServer

import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationScreen : Fragment(R.layout.fragment_registration_screen) {
    private val binding: FragmentRegistrationScreenBinding by viewBinding()
    private val viewModel: RegistrationScreenVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindLiveData()
        initRegistrationViewGroup()
        initSystemBackButton()
    }

    private fun initRegistrationViewGroup() {
        with(binding) {
            registrationViewGroup.setOnClickListener {
                registrationViewGroup.isEnabled = false //  Кликабельность
                changeVisibilityTwoView(progressBar, true, registrationBt, false)
                val modelSendDataOnServer = ModelSendDataOnServer(
                    userNameEt.text.toString(), userPasswordEt.text.toString()
                )
                viewModel!!.getResponseServer(modelSendDataOnServer)
            }
        }
    }

    private fun bindLiveData() {
        viewModel!!.getLiveDataModel().observe(viewLifecycleOwner, {
            changeVisibilityView(binding.progressBar, false)
            stepOnFragmentNotesScreen()

        })

        viewModel!!.getLiveDatError().observe(viewLifecycleOwner, {
            changeVisibilityTwoView(binding.progressBar, false, binding.registrationBt, true)
            createDialogError(it)
            binding.registrationViewGroup.isEnabled = true

        })

        viewModel!!.getLiveDataUserDataEmpty().observe(viewLifecycleOwner, {
            changeVisibilityTwoView(binding.progressBar, false, binding.registrationBt, true)
            createDialogError(it.toString())
            binding.registrationViewGroup.isEnabled = true
        })
    }

    private fun stepOnFragmentNotesScreen() {
        findNavController().navigate(R.id.action_registrationScreen_to_notesScreen)
    }


    private fun changeVisibilityView(view: View, isVisible: Boolean) {
        view.isVisible = isVisible
    }

    private fun changeVisibilityTwoView(
        view1: View,
        isVisibleView1: Boolean,
        view2: View,
        isVisibleView2: Boolean
    ) {
        view1.isVisible = isVisibleView1
        view2.isVisible = isVisibleView2
    }

    private fun initSystemBackButton() {
        with(requireActivity()) {
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
                OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finish()
                }
            })
        }
    }

    private fun createDialogError(messageError: String) {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(resources.getString(R.string.alert_dialog_title))
        builder.setMessage(messageError)
        builder.setPositiveButton(
            android.R.string.ok
        ) { dialog, which ->
            dialog.dismiss()
        }
        builder.create().show()
    }


}

