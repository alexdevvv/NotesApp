package com.example.notesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.FragmentTransaction
import com.example.notesapp.registration.registration_screen.RegistrationScreen

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val ft: FragmentTransaction = supportFragmentManager.beginTransaction()
        val registrationScreen = RegistrationScreen()
        ft.replace(R.id.fragment_container, registrationScreen).commit()

    }
}