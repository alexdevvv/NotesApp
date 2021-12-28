package com.example.notesapp.presentation.registration.notes_screen

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNotesScreenBinding

class NotesScreen : Fragment(R.layout.fragment_notes_screen) {
    private val binding: FragmentNotesScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonBackOnRegistrationScreen()
        initSystemBackButton()
    }

    private fun buttonBackOnRegistrationScreen(){
        binding.backBt.setOnClickListener {
            findNavController().navigate(R.id.action_notesScreen_to_registrationScreen)
        }
    }


    private fun initSystemBackButton() {
        with(requireActivity()) {
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
                OnBackPressedCallback(true){
                override fun handleOnBackPressed() {
                    finish()
                }
            })
        }
    }


}