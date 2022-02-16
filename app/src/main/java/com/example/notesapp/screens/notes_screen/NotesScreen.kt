package com.example.notesapp.screens.notes_screen

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.data.IS_USER_LOGGED_IN
import com.example.notesapp.databinding.FragmentNotesScreenBinding
import com.example.notesapp.domain.model.Todo
import com.example.notesapp.screens.notes_screen.recycler_view.TodosAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesScreen : Fragment(R.layout.fragment_notes_screen) {
    private val binding: FragmentNotesScreenBinding by viewBinding()
    private val viewModel: NotesScreenVM by viewModel()
    private var adapter: TodosAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
        bindLiveData()
        initSystemBackButton()
        initFloatingActionButton()
        initLogoutButton()


//        itemTouchHelper.attachToRecyclerView(binding.recyclerView)
    }

//    private fun initRecyclerView() {
//        with(binding) {
//            recyclerView.layoutManager = LinearLayoutManager(requireContext())
//            adapter = TodosAdapter(mutableListOf()) {
//                findNavController().navigate(
//                    R.id.action_notesScreen_to_newTodoScreen
//                )
//            }
//        }
//    }

    private fun initRecyclerView() {
        with(binding) {
            recyclerView.layoutManager = LinearLayoutManager(requireContext())
            adapter = TodosAdapter(mutableListOf())
        }
    }

    private fun initLogoutButton(){
        binding.logoutBt.setOnClickListener {
            val preferences = requireActivity().getPreferences(Context.MODE_PRIVATE)
            preferences.edit().remove(IS_USER_LOGGED_IN).apply()
            findNavController().navigate(R.id.action_notesScreen_to_generalScreen)
        }
    }

    private fun bindLiveData() {
        viewModel.getTodosLiveData().observe(viewLifecycleOwner,
            {
                adapter?.updateData(it)
            }
        )
        viewModel.getTodosFromDb()
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

//    private var itemTouchHelper = ItemTouchHelper(object :
//        ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
//        override fun onMove(
//            recyclerView: RecyclerView,
//            viewHolder: RecyclerView.ViewHolder,
//            target: RecyclerView.ViewHolder
//        ): Boolean {
//            return false
//        }
//
//        override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
//            viewModel.getLiveDataModel
//                .removeAt(viewHolder.adapterPosition)
//            binding.recyclerView.adapter!!.notifyDataSetChanged()
//        }
//    })

}