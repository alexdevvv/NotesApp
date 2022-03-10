package com.example.notesapp.screens.new_todo

import android.os.Bundle
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.data.PreferencesManager
import com.example.notesapp.databinding.FragmentNewTodoScreenBinding
import com.example.notesapp.domain.model.Todo
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewTodoScreen : Fragment(R.layout.fragment_new_todo_screen) {
    private val binding: FragmentNewTodoScreenBinding by viewBinding()
    private val viewModel: NewTodoScreenVM by viewModel()
    private val preferences: PreferencesManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAddNewTodoButton()
        initSystemBackButton()
    }

    private fun initAddNewTodoButton() {
        binding.saveNoteBt.setOnClickListener(View.OnClickListener {
            if (binding.addNameNoteEt.text.isNotEmpty()) {
                viewModel.insertTodo(
                    Todo(
                        preferences.getUserIdFromPref(), binding.addNameNoteEt.text.toString(), true
                    )
                )
                findNavController().navigate(R.id.action_newTodoScreen_to_notesScreen)
            }
        })
    }

    private fun initSystemBackButton() {
        with(requireActivity()) {
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
                OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    findNavController().navigate(R.id.action_newTodoScreen_to_notesScreen)
                }
            })
        }
    }

}