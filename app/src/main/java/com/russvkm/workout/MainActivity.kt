package com.russvkm.workout

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        supportActionBar?.hide()
        intendingToBmiActivity()
        intendingToHistoryActivity()
        startButtonLayout.setOnClickListener {
            intendingToOtherActivity()
        }
    }

    private fun intendingToOtherActivity(){
        startActivity(Intent(this@MainActivity,Workout::class.java))
    }
    private fun intendingToBmiActivity(){
        bmi.setOnClickListener {
            startActivity(Intent(this@MainActivity,Bmi::class.java))
        }
    }
    private fun intendingToHistoryActivity(){
        history.setOnClickListener {
            startActivity(Intent(this@MainActivity,History::class.java))
        }
    }
}
