package com.example.notesapp.screens.notes_screen.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.data.database.TodoEntity
import com.example.notesapp.domain.model.Todo

class TodosAdapter(private var todosList: List<Todo>, private val goToNewTodoScreen: () -> Unit):
    RecyclerView.Adapter<TodosAdapter.MyViewHolder>() {

    class MyViewHolder(private var itemView: View, private val goToNewTodoScreen: () -> Unit): RecyclerView.ViewHolder(itemView) {
        fun bindView(todoEntityDb: Todo){
            val todoName: TextView = itemView.findViewById(R.id.todo_name_tv)
            todoName.text = todoEntityDb.title

            itemView.setOnClickListener{
                goToNewTodoScreen()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)
        return MyViewHolder(itemView, goToNewTodoScreen)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(todosList[position])
    }

    override fun getItemCount(): Int {
        return todosList.size
    }

    fun updateData(list: List<Todo>){
        todosList = list
        notifyDataSetChanged()
    }
}