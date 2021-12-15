package com.example.notesapp.presentation.registration.registration_screen

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
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
        initRegistrationButton()


    }

    private fun initRegistrationButton() {
        binding.registrationBt.setOnClickListener() {
//            val fragmentNotesScreen = NotesScreen()
//            val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
//            fragmentTransaction?.replace(R.id.fragment_container, fragmentNotesScreen)?.commit()

            val modelSendDataOnServer = ModelSendDataOnServer(
                binding.userNameEt.text.toString(), binding.userPasswordEt.text.toString()
            )
            viewModel!!.getData(modelSendDataOnServer)
            method()

        }

    }

    fun method() {
        viewModel!!.liveData().observe(viewLifecycleOwner, {
            Log.e("XXX", it.username)
            it.id })

    }



}

