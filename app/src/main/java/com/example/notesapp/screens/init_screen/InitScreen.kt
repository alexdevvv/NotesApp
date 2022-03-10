package com.example.notesapp.screens.init_screen

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.data.PreferencesManager
import org.koin.android.ext.android.inject

class InitScreen : Fragment(R.layout.fragment_init_screen) {
    private val preferences: PreferencesManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isUserLoggedIn = preferences.getValueIsUserLoggedIn()

        if (isUserLoggedIn) {
            findNavController().navigate(R.id.action_initScreen_to_notesScreen)
        } else {
            findNavController().navigate(R.id.action_initScreen_to_generalScreen)
        }
    }
}