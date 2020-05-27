package com.russvkm.workout

data class ExerciseList(
        var id:Int, var exerciseName:String,
        val image:Int,
        var selected:Boolean,
        var completed:Boolean){
}