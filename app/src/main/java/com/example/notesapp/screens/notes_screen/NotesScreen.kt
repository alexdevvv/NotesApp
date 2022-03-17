package com.example.notesapp.screens.notes_screen

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.*
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.notesapp.R
import com.example.notesapp.data.PreferencesManager
import com.example.notesapp.databinding.FragmentNotesScreenBinding
import com.example.notesapp.domain.model.Todo
import com.example.notesapp.screens.notes_screen.recycler_view.TodosAdapter
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel

class NotesScreen : Fragment(R.layout.fragment_notes_screen), TextView.OnEditorActionListener {
    private val binding: FragmentNotesScreenBinding by viewBinding()
    private val viewModel: NotesScreenVM by viewModel()
    private var adapter: TodosAdapter = TodosAdapter()
    private val preferences: PreferencesManager by inject()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.init(preferences.getUserIdFromPref())
        bindLiveData()
        search()
        clearSearchText()
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
        if (item.itemId == R.id.logout_bt) {
            preferences.deleteUserIdFromPref()
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
        with(viewModel) {
            getTodosFromServerLiveData().observe(viewLifecycleOwner, {
                adapter.updateData(it  as MutableList<Todo>)
            })

            getTodosFromBDLiveData().observe(viewLifecycleOwner, {
                adapter.updateData(it)
            })

            getFilterTodosLiveData().observe(viewLifecycleOwner, {
                adapter.updateData(it)
            })

            getDataDeleteTodo().observe(viewLifecycleOwner, {
                Toast.makeText(
                    requireContext(),
                    it,
                    Toast.LENGTH_LONG
                ).show()
            })
        }
    }

    private fun search() {
        binding.searchEt.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun afterTextChanged(searchText: Editable?) {
                viewModel.filterTodos(searchText.toString())
            }

        })
    }

    private fun clearSearchText() {
        binding.clearTextTv.setOnClickListener {
            binding.searchEt.text = null
            hideKeyboard()
            binding.searchEt.clearFocus()
            viewModel.getTodosFromDb(
            )
        }
    }

    override fun onEditorAction(p0: TextView?, p1: Int, p2: KeyEvent?): Boolean {
        if (p1 == EditorInfo.IME_ACTION_DONE) {
            hideKeyboard()
        }
        return true
    }

    private fun hideKeyboard() {
        val inp: InputMethodManager =
            requireActivity().getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        inp.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)

    }

    private fun deleteTodo() {
        val itemTouchCallback = object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return true
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val todo = viewModel.getTodosFromBDLiveData().value?.get(viewHolder.adapterPosition)
                todo?.let { viewModel.deleteTodo(it) }
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
        binding.floatingAddTodoBt.setOnClickListener {
            addNewTodo()
        }
    }
}