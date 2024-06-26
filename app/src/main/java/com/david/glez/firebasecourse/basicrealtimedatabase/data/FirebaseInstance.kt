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

    fun writeOnFireBase(title: String, description: String) {
        val randomValue = Random.nextInt(1, 200)
        val newItem = myRef.push()
        newItem.setValue(Todo(title = title, description = description))
    }

    fun setUpDatabaseListener(postListener: ValueEventListener) {
        database.reference.addValueEventListener(postListener)
    }

    /*private fun getGenericTodoTaskItem(randomValue: String) =
        Todo(title = "Task $randomValue", description = "This is a simple description")*/

    fun removeFromDatabase(reference: String) {
        myRef.child(reference).removeValue()
    }

    fun updateFromDatabase(reference: String) {
        myRef.child(reference).child("done").setValue(true)
    }
}