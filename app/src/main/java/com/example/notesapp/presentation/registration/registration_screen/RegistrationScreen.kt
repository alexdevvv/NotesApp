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

class RegistrationScreen : Fragment(R.layout.fragment_registration_screen) {
    private val binding: FragmentRegistrationScreenBinding by viewBinding()
    private var viewModel: RegistrationScreenVM? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this).get(RegistrationScreenVM::class.java)
        bindLiveData()
        initRegistrationViewGroup()

    }

    private fun initRegistrationViewGroup() {
        binding.registrationViewGroup.setOnClickListener {
            changeViewVisibility(binding.progressBar, true)
            binding.registrationViewGroup.isEnabled = false
            binding.registrationBt.isVisible = false
            val modelSendDataOnServer = ModelSendDataOnServer(
                binding.userNameEt.text.toString(), binding.userPasswordEt.text.toString()
            )
            viewModel!!.getResponseServer(modelSendDataOnServer)
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

