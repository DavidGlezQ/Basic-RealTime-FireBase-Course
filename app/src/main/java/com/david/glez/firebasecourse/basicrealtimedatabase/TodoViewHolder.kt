package com.david.glez.firebasecourse.basicrealtimedatabase

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.david.glez.firebasecourse.basicrealtimedatabase.data.Todo
import com.david.glez.firebasecourse.basicrealtimedatabase.databinding.ItemTodoBinding

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemTodoBinding.bind(view)

    fun bind(todoTask: Pair<String, Todo>) {
        binding.tvTitle.text = todoTask.second.title
        binding.tvDescription.text = todoTask.second.description

    }
}