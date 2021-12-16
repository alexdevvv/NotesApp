package com.example.notesapp.presentation.registration.registration_screen

import android.app.AlertDialog
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentRegistrationScreenBinding
import com.example.notesapp.domain.model.ModelSendDataOnServer
import com.example.notesapp.presentation.registration.notes_screen.NotesScreen

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
            val modelSendDataOnServer = ModelSendDataOnServer(
                binding.userNameEt.text.toString(), binding.userPasswordEt.text.toString()
            )
            viewModel!!.getResponseServer(modelSendDataOnServer)
            getUserData()

        }

    }

    fun getUserData() {
        viewModel!!.getLiveDataModel().observe(viewLifecycleOwner, {
            if (it.id != 0L) {
                val fragmentNotesScreen = NotesScreen()
                val fragmentTransaction = activity?.supportFragmentManager?.beginTransaction()
                fragmentTransaction?.replace(R.id.fragment_container, fragmentNotesScreen)?.commit()
            }else{
                createDialogError()
            }
        })
    }

    private fun createDialogError() {
        val builder = AlertDialog.Builder(activity)
        builder.setTitle(R.string.alert_dialog_title)
        builder.setMessage(R.string.alert_dialog_message)
        builder.setCancelable(true)
        builder.setNegativeButton(
            android.R.string.ok
        ) { dialog, which ->
            // Кнопка ОК
            dialog.dismiss() // Отпускает диалоговое окно
        }
        builder.create().show()
    }

}

