package com.example.notesapp.screens.notes_screen

import android.text.Editable
import androidx.lifecycle.ViewModel
import com.example.notesapp.domain.model.Todo

class NotesScreenVM : ViewModel() {

    val id = 0;
    private var listTodo: ArrayList<Todo> = ArrayList()
    fun getListTodo() = listTodo

//    private var liveDataTodo = MutableLiveData<ArrayList<Todo>>()
//    fun getLiveDataTodo(): LiveData<ArrayList<Todo>> = liveDataTodo

    fun addTodo(todoName: String) {
        listTodo.add(Todo(id + 1, todoName, true))
    }

}