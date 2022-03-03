package com.example.notesapp.screens.init_screen

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.notesapp.R
import com.example.notesapp.data.IS_USER_LOGGED_IN


class InitScreen : Fragment(R.layout.fragment_init_screen) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val preferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
        val isUserLoggedIn = preferences.getBoolean(IS_USER_LOGGED_IN, false)

        if(isUserLoggedIn) {
            findNavController().navigate(R.id.action_initScreen_to_notesScreen)
        }else{
            findNavController().navigate(R.id.action_initScreen_to_generalScreen)
        }
    }
}