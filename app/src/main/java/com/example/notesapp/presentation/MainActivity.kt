package com.example.notesapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.notesapp.R
import com.example.notesapp.presentation.registration.registration_screen.RegistrationScreen

class MainActivity : AppCompatActivity() {

    private val registrationScreen = RegistrationScreen()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(registrationScreen)

    }

   private fun replaceFragment(fragment: Fragment) {
        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        ft.replace(R.id.fragment_container, fragment).commit()
    }

}