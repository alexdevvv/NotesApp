package com.example.notesapp.screens.login_screen

import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentLoginScreenBinding
import com.example.notesapp.domain.model.UserModel
import com.example.notesapp.screens.createDialog
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginScreen : Fragment(R.layout.fragment_login_screen) {

    private val binding: FragmentLoginScreenBinding by viewBinding()
    private val viewModel: LoginScreenVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindLivedata()
        initRegistrationViewGroup()

        view.setOnClickListener{
            hideKeyboard(requireActivity())
        }
    }

    private fun initRegistrationViewGroup() {
        with(binding) {
            registrationViewGroup.setOnClickListener {
                changeVisibilityTwoView(true)
                registrationViewGroup.isEnabled = false //  Кликабельность
                val modelSendDataOnServer = UserModel(
                    userNameEt.text.toString(), userPasswordEt.text.toString()
                )
                viewModel.login(modelSendDataOnServer)
            }
        }
    }

    private fun bindLivedata(){
        with(viewModel) {
            getLiveDataModel().observe(viewLifecycleOwner,{
                changeVisibilityView(binding.progressBar, false)
                findNavController().navigate(R.id.action_loginScreen_to_notesScreen)
            })

            getLiveDataError().observe(viewLifecycleOwner, {
                changeVisibilityTwoView(isCheckedLoad = false)
                binding.registrationViewGroup.isEnabled = true
                createDialog(it, requireActivity())
            })

            getLiveDataUserDataEmpty().observe(viewLifecycleOwner, {
                changeVisibilityTwoView(isCheckedLoad = false)
                binding.registrationViewGroup.isEnabled = true
                createDialog(getString(R.string.empty_fields), requireActivity())
            })
        }
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

    private fun hideKeyboard(activity: Activity) {
        val view = activity.findViewById<View>(android.R.id.content)
        if (view != null) {
            val imm: InputMethodManager =
                activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }


}