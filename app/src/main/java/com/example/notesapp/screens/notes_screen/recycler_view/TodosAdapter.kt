package com.example.notesapp.screens.notes_screen.recycler_view

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.notesapp.R
import com.example.notesapp.domain.model.Todo
import java.util.*

class TodosAdapter : RecyclerView.Adapter<TodosAdapter.MyViewHolder>() {

    var todosList = mutableListOf<Todo>()
    var todoFilterList: MutableList<Todo> = mutableListOf()

    init {
        todoFilterList = todosList
    }

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

}

//    override fun getFilter(): Filter {
//        return object : Filter() {
//            override fun performFiltering(constraint: CharSequence?): FilterResults {
//                val charSearch = constraint.toString()
//                if(charSearch.isEmpty()) {
//                    todoFilterList = todosList
//                } else {
//                    var resultList: MutableList<Todo> = mutableListOf()
//                    for (row in todosList) {
//                        if (row.title.lowercase(Locale.ROOT)
//                                .contains(charSearch.lowercase(Locale.ROOT))
//                        ) {
//                            resultList.add(row)
//                        }
//                    }
//                    todoFilterList = resultList
//                }
//                val filterResults = FilterResults()
//                filterResults.values = todoFilterList
//                return filterResults
//            }
//
//            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
//                    todoFilterList = results?.values as MutableList<Todo>
//                Log.e("RESULT_TODO", todoFilterList.size.toString())
//                notifyDataSetChanged()
////                updateData(todoFilterList)
//
//            }
//        }
//    }




//    fun searchInAdapter(searchText: String) {
//        bufferList = todosList
//        for (todo in todosList){
//            if (todo.title.toLowerCase().contains(searchText.toLowerCase())){
//                resultSearchList.add(todo)
//                updateData(resultSearchList)
//            }
//        }
//    }
//
//    fun clearSearch(){
//        resultSearchList.clear()
//        updateData(bufferList)