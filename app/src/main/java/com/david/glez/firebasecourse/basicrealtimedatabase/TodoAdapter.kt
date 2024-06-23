package com.david.glez.firebasecourse.basicrealtimedatabase

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.david.glez.firebasecourse.basicrealtimedatabase.data.Todo

class TodoAdapter(private var todoList: List<Pair<String, Todo>> = emptyList()) :
    RecyclerView.Adapter<TodoViewHolder>() {

    fun setNewList(data: List<Pair<String, Todo>>) {
        todoList = data
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder {
        return TodoViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false)
        )
    }

    override fun getItemCount() = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }
}