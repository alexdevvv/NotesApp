package com.example.notesapp.screens.notes_screen.recycler_view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.domain.model.Todo

class TodosAdapter: RecyclerView.Adapter<TodosAdapter.MyViewHolder>(){

    var todosList: MutableList<Todo> = mutableListOf()
    var resultSearchList: MutableList<Todo> = mutableListOf()
    var bufferList: MutableList<Todo> = mutableListOf()

    class MyViewHolder(private var itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bindView(todoEntityDb: Todo) {
            val todoName: TextView = itemView.findViewById(R.id.todo_name_tv)
            todoName.text = todoEntityDb.title
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

    fun updateData(list: MutableList<Todo>) {
        todosList = list
        notifyDataSetChanged()
    }

    fun delete(position: Int) {
        todosList.removeAt(position)
        notifyItemRemoved(position)
    }

    fun searchInAdapter(searchText: String) {
        bufferList = todosList
        for (todo in todosList){
            if (todo.title.toLowerCase().contains(searchText.toLowerCase())){
                resultSearchList.add(todo)
                updateData(resultSearchList)
            }
        }
    }

    fun clearSearch(){
        resultSearchList.clear()
        updateData(bufferList)
    }
}