package com.example.notesapp.screens.notes_screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.data.IS_USER_LOGGED_IN
import com.example.notesapp.databinding.FragmentNotesScreenBinding
import com.example.notesapp.domain.model.Todo
import com.example.notesapp.screens.notes_screen.recycler_view.TodosAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesScreen : Fragment(R.layout.fragment_notes_screen){
    private val binding: FragmentNotesScreenBinding by viewBinding()
    private val viewModel: NotesScreenVM by viewModel()
    private var adapter: TodosAdapter = TodosAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindLiveData()
        setHasOptionsMenu(true)
        initRecyclerView()
        deleteTodo()
        initSystemBackButton()
        initFloatingActionButton()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if(item.itemId == R.id.logout_bt){
            val preferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            preferences.edit().remove(IS_USER_LOGGED_IN).apply()
            findNavController().navigate(R.id.action_notesScreen_to_generalScreen)
        }
        return true
    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            recyclerView.adapter = adapter
        }
    }

    private fun bindLiveData() {
        viewModel.getTodosLiveData().observe(viewLifecycleOwner,
            {
                adapter.updateData(it as MutableList<Todo>)

            }
        )
        viewModel.getTodosFromDb()


    }

    private fun deleteTodo() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                TODO("Not yet implemented")
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val todo = adapter.todosList[viewHolder.adapterPosition]
                viewModel.deleteTodo(todo)
                Toast.makeText(requireContext(), viewModel.getDataDeleteTodo().value, Toast.LENGTH_LONG).show()
                adapter.delete(viewHolder.adapterPosition)

            }
        }
        val itemTouchHelper = ItemTouchHelper(itemTouchCallback)
        itemTouchHelper.attachToRecyclerView(binding.recyclerView)

    }

    private fun addNewTodo() {
        findNavController().navigate(R.id.action_notesScreen_to_newTodoScreen)
    }

    private fun initSystemBackButton() {
        with(requireActivity()) {
            onBackPressedDispatcher.addCallback(viewLifecycleOwner, object :
                OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    finish()
                }
            })
        }
    }

    private fun initFloatingActionButton() {
        binding.floatingAddTodoBt.setOnClickListener(View.OnClickListener {
            addNewTodo()
        })
    }


}