package com.russvkm.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_finish.*

class FinishActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        configuringToolBar()
    }

    private fun configuringToolBar(){
        finishActivityToolBar.title="HEALTH"
        setSupportActionBar(finishActivityToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        finishActivityToolBar.setNavigationOnClickListener{
            onBackPressed()
        }
    }
}
