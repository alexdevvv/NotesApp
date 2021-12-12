package com.example.notesapp.registration.registration_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentRegistrationScreenBinding
import com.example.notesapp.registration.notes_screen.NotesScreen


class RegistrationScreen : Fragment(R.layout.fragment_registration_screen) {
    private val binding: FragmentRegistrationScreenBinding by viewBinding()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRegistrationButton()
    }


    private fun initRegistrationButton() {
        binding.registrationBt.setOnClickListener() {
            val fragmentNotesScreen = NotesScreen()
            val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragment_container, fragmentNotesScreen)?.commit()
        }

    }

}