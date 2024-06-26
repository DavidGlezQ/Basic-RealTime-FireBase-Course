package com.david.glez.firebasecourse.basicrealtimedatabase

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.ActionBar.LayoutParams
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.david.glez.firebasecourse.basicrealtimedatabase.data.FirebaseInstance
import com.david.glez.firebasecourse.basicrealtimedatabase.data.Todo
import com.david.glez.firebasecourse.basicrealtimedatabase.databinding.ActivityMainBinding
import com.david.glez.firebasecourse.basicrealtimedatabase.databinding.DialogAddTaskBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.getValue

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseInstance: FirebaseInstance
    private lateinit var todoAdapter: TodoAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseInstance = FirebaseInstance(this)
        setUI()
        setUpListeners()
    }

    private fun setUpListeners() {
        val postListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                /*val data: String? = snapshot.getValue<String>()
                binding.tvResult.text = data ?: ""*/
                val data = getCleanSnapshot(snapshot)
                todoAdapter.setNewList(data)
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("onCancelled", error.details)
            }

        }
        firebaseInstance.setUpDatabaseListener(postListener)
    }

    private fun showDialog() {
        val binding = DialogAddTaskBinding.inflate(layoutInflater)
        val dialog = Dialog(this)
        dialog.setContentView(binding.root)

        dialog.window?.setLayout(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        binding.btnAddTask.setOnClickListener {
            val title = binding.etTitle.text.toString()
            val description = binding.etDescription.text.toString()
            if (title.isBlank() || description.isBlank()) Toast.makeText(
                this,
                "The field can't be empty",
                Toast.LENGTH_SHORT
            ).show()
            else {
                firebaseInstance.writeOnFireBase(title = title, description = description)
                dialog.dismiss()
            }
        }
        dialog.show()
    }

    private fun getCleanSnapshot(snapshot: DataSnapshot): List<Pair<String, Todo>> {
        val list = snapshot.children.map { item ->
            Pair(item.key!!, item.getValue(Todo::class.java)!!)
        }
        return list
    }

    private fun setUI() {
        binding.button.setOnClickListener {
            showDialog()
        }
        todoAdapter = TodoAdapter { action, reference ->
            when (action) {
                Actions.DELETE -> firebaseInstance.removeFromDatabase(reference)

                Actions.DONE -> firebaseInstance.updateFromDatabase(reference)
            }
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }
    }
}