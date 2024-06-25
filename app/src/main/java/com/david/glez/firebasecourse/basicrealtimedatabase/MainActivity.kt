package com.david.glez.firebasecourse.basicrealtimedatabase

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.david.glez.firebasecourse.basicrealtimedatabase.data.FirebaseInstance
import com.david.glez.firebasecourse.basicrealtimedatabase.data.Todo
import com.david.glez.firebasecourse.basicrealtimedatabase.databinding.ActivityMainBinding
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

    private fun getCleanSnapshot(snapshot: DataSnapshot): List<Pair<String, Todo>> {
        val list = snapshot.children.map { item ->
            Pair(item.key!!, item.getValue(Todo::class.java)!!)
        }
        return list
    }

    private fun setUI() {
        binding.button.setOnClickListener {
            firebaseInstance.writeOnFireBase()
        }
        todoAdapter = TodoAdapter { reference ->
            firebaseInstance.removeFromDatabase(reference)
        }
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = todoAdapter
        }
    }
}