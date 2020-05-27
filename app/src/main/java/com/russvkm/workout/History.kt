package com.russvkm.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        conFiguringActionBar()
    }

    private fun conFiguringActionBar(){
        setSupportActionBar(historyToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        historyToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}
