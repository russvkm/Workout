package com.russvkm.workout

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_finish.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class FinishActivity : AppCompatActivity() {
    private var numberOfExercise=0

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_finish)
        configuringToolBar()
        val intent= intent
        numberOfExercise=intent.getIntExtra("ExerciseNumber",0)+1
        burnCalories.text="You burned ${numberOfExercise*10} calories"
        pushingDataToSQLite()
    }

    private fun configuringToolBar(){
        finishActivityToolBar.title="HEALTH"
        setSupportActionBar(finishActivityToolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        finishActivityToolBar.setNavigationOnClickListener{
            onBackPressed()
        }
    }


    @SuppressLint("SimpleDateFormat")
    private fun pushingDataToSQLite() {
        val calendar = Calendar.getInstance()
        val sdf = SimpleDateFormat("dd/MMM/yyyy hh:mm:ss a")
        val dateTime = sdf.format(calendar.time)
        val db=DataBaseHelper(this@FinishActivity)
        if(db.pushData(dateTime,numberOfExercise)>-1){
            Toast.makeText(this,"work out saved to history!",Toast.LENGTH_LONG).show()
        }else{
            Toast.makeText(this,"Work out can't save to history, Something went wrong:(",Toast.LENGTH_LONG).show()
        }

    }
}
