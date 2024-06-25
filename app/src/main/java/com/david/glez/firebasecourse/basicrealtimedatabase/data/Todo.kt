package com.david.glez.firebasecourse.basicrealtimedatabase.data

data class Todo(
    val title: String? = "",
    val description: String? = "",
    val done: Boolean? = false
    //all boolean need to be without 'is'
)
