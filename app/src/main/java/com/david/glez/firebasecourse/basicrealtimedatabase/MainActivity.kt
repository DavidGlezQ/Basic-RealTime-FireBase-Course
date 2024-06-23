package com.david.glez.firebasecourse.basicrealtimedatabase

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.david.glez.firebasecourse.basicrealtimedatabase.data.FirebaseInstance
import com.david.glez.firebasecourse.basicrealtimedatabase.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var firebaseInstance: FirebaseInstance

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        firebaseInstance = FirebaseInstance(this)
        setUI()
    }

    private fun setUI() {
        binding.button.setOnClickListener {
            firebaseInstance.writeOnFireBase()
        }
    }
}