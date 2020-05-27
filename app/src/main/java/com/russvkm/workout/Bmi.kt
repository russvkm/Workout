package com.russvkm.workout

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_bmi.*

class Bmi : AppCompatActivity() {

    private val metricState="METRIC_STATE_ACTIVE"
    private val usState="US_STATE_ACTIVE"
    private var currentState=metricState

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmi)
        bmiToolbar.title="BMI"
        setSupportActionBar(bmiToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        bmiToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        metricBmi()
        usBmi()
        metricState()
        unitRadioGroup.setOnCheckedChangeListener { _, checkedId ->
            if(checkedId==R.id.metricUnit){
                metricState()
            }else{
                usStateUnit()
            }
        }
    }

    private fun metricState(){
        currentState=metricState
        metricLayout.visibility= View.VISIBLE
        usUnitLayout.visibility=View.GONE
    }

    private fun usStateUnit(){
        currentState=usState
        metricLayout.visibility= View.GONE
        usUnitLayout.visibility=View.VISIBLE
    }

    @SuppressLint("SetTextI18n")
    private fun calculateBmi(){
        when{
            weightEditText.text.toString().isEmpty()-> {
                weightEditText.error="Enter Weight in Kg"
                weightEditText.requestFocus()
            }
            heightEditText.text.toString().isEmpty()->{
                heightEditText.error="Enter Height in cms"
                heightEditText.requestFocus()
            }else->{
            val height=heightEditText.text.toString().toDouble()
            val weight=weightEditText.text.toString().toDouble()
            val bmi=weight/((height/100)*(height/100))
            resultTextView.text="${String.format("%1.2f",bmi)}%"
            when(bmi){
                in 0.0..18.5->{resultTextView.text="${resultTextView.text}\nYou are under weight" +
                        "\n Need to gain ${String.format("%1.2f",(18.5-bmi)*((height/100)*(height/100)))} kg"
                }
                in 18.5..25.0 ->{resultTextView.text="${resultTextView.text}\nYou are Healthy \nYou are in good shape"}
                !in 0.0..25.0->{resultTextView.text="${resultTextView.text}\nYou are OverWeight" +
                        "\n Need to lose ${String.format("%1.2f",(bmi-25)*((height/100)*(height/100)))} kg"}
                }
            }
        }
    }
    private fun metricBmi(){
        calculateBmi.setOnClickListener {
            calculateBmi()
        }
    }
    @SuppressLint("SetTextI18n")
    private fun usBmi(){
        usCalculateBmi.setOnClickListener {
            when{
                usWeightEditText.text.toString().isEmpty()-> {
                    usWeightEditText.error="Enter Weight in lbs"
                    usWeightEditText.requestFocus()
                }
                usHeightEditText.text.toString().isEmpty()->{
                   usHeightEditText.error="Enter Height in inches"
                    usHeightEditText.requestFocus()
                }
                usFeetEditText.text.toString().isEmpty()->{
                    usFeetEditText.error="Enter Height in Feet"
                    usFeetEditText.requestFocus()
                }
                else->{
                val height=((usFeetEditText.text.toString().toDouble())*12)+usHeightEditText.text.toString().toDouble()
                val weight=usWeightEditText.text.toString().toDouble()
                val bmi=(weight/((height)*(height)))*703
                usResultTextView.text="${String.format("%1.2f",bmi)}%"
                when(bmi){
                    in 0.0..18.5->{usResultTextView.text="${usResultTextView.text}\nYou are under weight" +
                            "\n Need to gain ${String.format("%1.2f",((18.5-bmi)*((height)*(height)))/703)} lbs"
                    }
                    in 18.5..25.01 ->{usResultTextView.text="${usResultTextView.text}\nYou are Healthy \nYou are in good shape"}

                    !in 0.0..25.00->{usResultTextView.text="${usResultTextView.text}\nYou are OverWeight" +
                            "\n Need to lose ${String.format("%1.2f",((bmi-25)*((height)*(height)))/703)} lbs"}
                    }
                }
            }
        }
    }
}
