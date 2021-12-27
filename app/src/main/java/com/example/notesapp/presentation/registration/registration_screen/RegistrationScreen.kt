package com.example.notesapp.presentation.registration.registration_screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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

    }

    private fun initRegistrationViewGroup() {
        with(binding){
            registrationViewGroup.setOnClickListener {
                changeViewVisibility(progressBar, true)
                registrationViewGroup.isEnabled = false
                registrationBt.isVisible = false
                val modelSendDataOnServer = ModelSendDataOnServer(
                    userNameEt.text.toString(), userPasswordEt.text.toString()
                )
                viewModel!!.getResponseServer(modelSendDataOnServer)
            }
        }
    }

    private fun bindLiveData() {
        viewModel!!.getLiveDataModel().observe(viewLifecycleOwner, {
            changeViewVisibility(binding.progressBar, false)
            stepOnFragmentNotesScreen()

        })

        viewModel!!.getLiveDatError().observe(viewLifecycleOwner, {
            changeViewVisibility(binding.progressBar, false)
            createDialogError(it)
            changeViewVisibility(binding.registrationBt, true)
            binding.registrationViewGroup.isEnabled = true

        })

        viewModel!!.getLiveDataUserDataEmpty().observe(viewLifecycleOwner, {
            changeViewVisibility(binding.progressBar, false)
            createDialogError(it.toString())
            changeViewVisibility(binding.registrationBt, true)
            binding.registrationViewGroup.isEnabled = true
        })
    }

    private fun stepOnFragmentNotesScreen() {
        findNavController().navigate(R.id.action_registrationScreen_to_notesScreen)
    }


    private fun changeViewVisibility(view: View, isVisible: Boolean) {
        view.isVisible = isVisible
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

