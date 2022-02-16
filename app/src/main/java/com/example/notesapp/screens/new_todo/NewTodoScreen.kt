package com.example.notesapp.screens.new_todo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.databinding.FragmentNewTodoScreenBinding
import com.example.notesapp.domain.model.Todo
import com.example.notesapp.screens.notes_screen.NotesScreenVM
import org.koin.androidx.viewmodel.ext.android.viewModel

class NewTodoScreen : Fragment(R.layout.fragment_new_todo_screen) {
    private val binding: FragmentNewTodoScreenBinding by viewBinding()
    private val viewModel: NewTodoScreenVM by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initAddNewTodoButton()
    }

   private fun initAddNewTodoButton() {
        binding.saveNoteBt.setOnClickListener(View.OnClickListener {
            if (binding.addNameNoteEt.text.isNotEmpty()) {
                viewModel.insertTodo(Todo(binding.addNameNoteEt.text.toString(), true) )
                findNavController().navigate(R.id.action_newTodoScreen_to_notesScreen)
            }

        })
    }


}