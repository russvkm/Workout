package com.russvkm.workout

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.speech.tts.TextToSpeech
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_workout.*
import kotlinx.android.synthetic.main.exit_button_dialog_box.*
import java.util.*

class Workout : AppCompatActivity(), TextToSpeech.OnInitListener{

    private var timeCountDown:CountDownTimer?=null
    private var currentProgress=0
    private var exerciseProgress=0
    private var exerciseList:ArrayList<ExerciseList>?=null
    private var exerciseTimeCountDown:CountDownTimer?=null
    private var exerciseIndex=-1
    private var textToSpeech:TextToSpeech?=null
    private var exerciseNumberAdapter:ExerciseNumberAdapter?=null
    var player:MediaPlayer?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        actionBar()
        setProgress()
        exerciseList=ExerciseListObject.exerciseListFun()
        textToSpeech= TextToSpeech(this,this)
        configureQuestionNumberRecyclerView()
    }

    private fun actionBar(){
        appName.title="HEALTH"
        setSupportActionBar(appName)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        appName.setNavigationOnClickListener{
            customDialogBox()
        }
    }
    private fun countDownTimer(){
        starterProgressBar.progress=currentProgress
       timeCountDown=object :CountDownTimer(10000,1000){
           @SuppressLint("SetTextI18n")
           override fun onFinish() {
               countDownTimer.text="Go!"
               exerciseIndex++
               val exercise:ExerciseList?=exerciseList!![exerciseIndex]
                exerciseList!![exerciseIndex].selected=true
               exerciseNumberAdapter!!.notifyDataSetChanged()
               exerciseName.text=exercise!!.exerciseName
               exerciseImage.setImageResource(exercise.image)
               textToSpeech("Do the exercise ${exercise.exerciseName}")
               setExerciseProgress()
           }
           override fun onTick(millisUntilFinished: Long) {
               currentProgress++
               starterProgressBar.progress=10-currentProgress
               countDownTimer.text="${10-currentProgress}"
               speakingCounting(10,currentProgress)
           }
       }.start()
    }
    private fun setProgress(){
        try {
            player= MediaPlayer.create(applicationContext,R.raw.press_start)
            player!!.start()
        }catch(e:Exception){e.printStackTrace()}
        exerciseContainerLinearLayout.visibility= View.INVISIBLE
        ButtonContainerLinearLayout.visibility=View.VISIBLE
        if(timeCountDown!=null){
            timeCountDown?.cancel()
            currentProgress=0
        }
        countDownTimer()
    }
    override fun onDestroy() {
        super.onDestroy()
        if(timeCountDown!=null){
            timeCountDown!!.cancel()
            currentProgress=0
        }
        if(exerciseTimeCountDown!=null){
            exerciseTimeCountDown!!.cancel()
            exerciseProgress=0
        }
        if(player!=null){
            player!!.stop()
            player=null
        }
    }
    private fun exerciseTimer(){
        exerciseProgressBar.progress=exerciseProgress
        exerciseTimeCountDown=object: CountDownTimer(60000,1000){
            @SuppressLint("SetTextI18n")
            override fun onTick(millisUntilFinished: Long) {
                exerciseProgress++
                exerciseProgressBar.progress=60-exerciseProgress
                exerciseCountDownTimer.text="${60-exerciseProgress}"
                speakingCounting(60,exerciseProgress)
            }
            @SuppressLint("SetTextI18n")
            override fun onFinish() {
                if(exerciseIndex<exerciseList!!.size-1){
                    exerciseList!![exerciseIndex].selected=false
                    exerciseList!![exerciseIndex].completed=true
                    exerciseNumberAdapter!!.notifyDataSetChanged()
                    val exercise:ExerciseList?=exerciseList!![exerciseIndex+1]
                    upComingExercise.text="Upcoming Exercise \n${exercise!!.exerciseName}"
                    textToSpeech("${exerciseList!![exerciseIndex].exerciseName} completed. Take Rest! Upcoming Exercise \n${exercise.exerciseName}")
                    setProgress()
                }else{
                    finish()
                    startActivity(Intent(this@Workout,FinishActivity::class.java))
                }
            }
        }.start()
    }
    private fun setExerciseProgress(){
        exerciseContainerLinearLayout.visibility= View.VISIBLE
        ButtonContainerLinearLayout.visibility=View.INVISIBLE
        if(exerciseTimeCountDown!=null){
            exerciseTimeCountDown?.cancel()
            exerciseProgress=0
        }
        exerciseTimer()
    }
    override fun onInit(status: Int) {
        textToSpeech("Welcome to workout! lets start! Upcoming exercise is Jumping Jacks")
        if(status==TextToSpeech.SUCCESS){
            val result=textToSpeech!!.setLanguage(Locale.ENGLISH)
            if(result==TextToSpeech.LANG_MISSING_DATA||result==TextToSpeech.LANG_NOT_SUPPORTED){
                Toast.makeText(this,"$result not supported in your device",Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun textToSpeech(text:String){
        textToSpeech!!.speak(text,TextToSpeech.QUEUE_FLUSH,null,"")
    }
    private fun speakingCounting(currentSecond:Int,progress:Int){
        when(currentSecond-progress){
            3->textToSpeech("Three")
            2->textToSpeech("Two")
            1->textToSpeech("One")
            0->textToSpeech("Zero")
        }
    }
    private fun configureQuestionNumberRecyclerView(){
        recyclerViewExercise.layoutManager=LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        exerciseNumberAdapter= ExerciseNumberAdapter(exerciseList!!,this)
        recyclerViewExercise.adapter=exerciseNumberAdapter
    }
    private fun customDialogBox(){
        val dialog= Dialog(this@Workout)
        dialog.setContentView(R.layout.exit_button_dialog_box)
        dialog.yes.setOnClickListener {
            finish()
            dialog.dismiss()
        }
        dialog.no.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }
}
