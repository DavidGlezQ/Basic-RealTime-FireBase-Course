package com.david.glez.firebasecourse.basicrealtimedatabase.data

import android.content.Context
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kotlin.random.Random


class FirebaseInstance(context: Context) {

    private val database = Firebase.database
    private val myRef = database.reference


    init {
        FirebaseApp.initializeApp(context)
    }

    fun writeOnFireBase() {
        val randomValue = Random.nextInt(1, 200)
        val newItem = myRef.push()
        newItem.setValue(getGenericTodoTaskItem(randomValue = randomValue.toString()))
    }

    fun setUpDatabaseListener(postListener: ValueEventListener) {
        database.reference.addValueEventListener(postListener)
    }

    private fun getGenericTodoTaskItem(randomValue: String) =
        Todo(title = "Task $randomValue", description = "This is a simple description")
}