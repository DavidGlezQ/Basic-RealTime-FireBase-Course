package com.david.glez.firebasecourse.basicrealtimedatabase

import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.david.glez.firebasecourse.basicrealtimedatabase.data.Todo
import com.david.glez.firebasecourse.basicrealtimedatabase.databinding.ItemTodoBinding

class TodoViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemTodoBinding.bind(view)

    fun bind(todoTask: Pair<String, Todo>, onItemSelected: (Actions, String) -> Unit) {
        binding.tvTitle.text = todoTask.second.title
        binding.tvDescription.text = todoTask.second.description
        binding.tvReference.text = todoTask.first
        //binding.cvItem.setOnClickListener { onItemSelected(todoTask.first) }
        binding.iconDelete.setOnClickListener { onItemSelected(Actions.DELETE, todoTask.first) }
        binding.iconDone.setOnClickListener { onItemSelected(Actions.DONE, todoTask.first) }
        val color = if (todoTask.second.done == true) {
            R.color.gold
        } else {
            R.color.black
        }
        binding.cvItem.strokeColor = ContextCompat.getColor(binding.cvItem.context, color)
    }
}