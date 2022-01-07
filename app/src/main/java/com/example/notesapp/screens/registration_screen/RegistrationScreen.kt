package com.example.notesapp.screens.registration_screen

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

import org.koin.androidx.viewmodel.ext.android.viewModel
import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
import com.example.notesapp.domain.model.ModelSendDataOnServer


class RegistrationScreen : Fragment(R.layout.fragment_registration_screen) {
    private val binding: FragmentRegistrationScreenBinding by viewBinding()
    private val viewModel: RegistrationScreenVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindLiveData()

        initRegistrationViewGroup()
        initSystemBackButton()

        view.setOnClickListener{
            hideKeyboard(requireActivity())
        }
    }

    fun hideKeyboard(activity: Activity) {
        val view = activity.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

    private fun initRegistrationViewGroup() {
        with(binding) {
            registrationViewGroup.setOnClickListener {
                registrationViewGroup.isEnabled = false //  Кликабельность
                changeVisibilityTwoView(isCheckedLoad = true)
                val modelSendDataOnServer = ModelSendDataOnServer(
                    userNameEt.text.toString(), userPasswordEt.text.toString()
                )
                viewModel.getResponseServer(modelSendDataOnServer)
            }
        }
    }

    private fun bindLiveData() {
        with(viewModel) {
            getLiveDataModel().observe(viewLifecycleOwner, {
                changeVisibilityView(binding.progressBar, false)
                stepOnFragmentNotesScreen()

            })

            getLiveDatError().observe(viewLifecycleOwner, {
                changeVisibilityTwoView(isCheckedLoad = false)
                createDialogError(it)
                binding.registrationViewGroup.isEnabled = true

            })

            getLiveDataUserDataEmpty().observe(viewLifecycleOwner, {
                changeVisibilityTwoView(isCheckedLoad = false)
                createDialogError(it.toString())
                binding.registrationViewGroup.isEnabled = true
            })
        }

    }

    private fun stepOnFragmentNotesScreen() {
        findNavController().navigate(R.id.action_registrationScreen_to_notesScreen)
    }


    private fun changeVisibilityView(view: View, isVisible: Boolean) {
        view.isVisible = isVisible
    }

    private fun changeVisibilityTwoView(isCheckedLoad: Boolean) {
        if(isCheckedLoad) {
            binding.progressBar.isVisible = true;
            binding.registrationBt.isVisible = false;
        } else {
            binding.progressBar.isVisible = false;
            binding.registrationBt.isVisible = true;
        }
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

