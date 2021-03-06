package com.example.notesapp.screens.notes_screen.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.domain.model.ModelTodo

class TodosAdapter : RecyclerView.Adapter<TodosAdapter.MyViewHolder>() {

    private var todosList = mutableListOf<ModelTodo>()

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(modelTodo: ModelTodo) {
            val todoName: TextView = itemView.findViewById(R.id.todo_name_tv)
            todoName.text = modelTodo.title
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.todo_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bindView(todosList[position])
    }

    override fun getItemCount(): Int {
        return todosList.size
    }

    fun updateData(list: MutableList<ModelTodo>?) {
        if (list != null) {
            todosList = list
        }
        notifyDataSetChanged()
    }

}