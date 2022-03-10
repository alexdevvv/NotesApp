package com.example.notesapp.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentGeneralScreenBinding

class GeneralScreen : Fragment(R.layout.fragment_general_screen) {
    private val binding: FragmentGeneralScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        stepToRegistrationScreenBt()
        stepToLoginScreenBt()
    }
    private fun stepToRegistrationScreenBt() {
        binding.parentRegistrationBt.setOnClickListener {
            findNavController().navigate(R.id.action_generalScreen_to_registrationScreen)
        }
    }

    private fun stepToLoginScreenBt() {
        binding.parentCreateProfileBt.setOnClickListener {
            findNavController().navigate(R.id.action_generalScreen_to_loginScreen)
        }
    }


}