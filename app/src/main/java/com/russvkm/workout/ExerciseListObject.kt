package com.russvkm.workout

object ExerciseListObject{

     fun exerciseListFun():ArrayList<ExerciseList>{
        val exercise=ArrayList<ExerciseList>()
        val exeOne=ExerciseList(1,"Jumping Jacks",R.drawable.ic_jumping_jacks,false,false)
        exercise.add(exeOne)
        val exeTwo=ExerciseList(2,"Wall Sit",R.drawable.ic_wall_sit,false,false)
        exercise.add(exeTwo)
         val exeThree=ExerciseList(3,"Push Ups",R.drawable.ic_push_up,false,false)
         exercise.add(exeThree)
         val exeFour=ExerciseList(4,"Abdominal crunches",R.drawable.ic_abdominal_crunch,false,false)
         exercise.add(exeFour)
         val exeFive=ExerciseList(5,"Step Up Onto Chair",R.drawable.ic_step_up_onto_chair,false,false)
         exercise.add(exeFive)
         val exeSix=ExerciseList(6,"Squat",R.drawable.ic_squat,false,false)
         exercise.add(exeSix)
         val exeSeven=ExerciseList(7,"Triceps Dip On Chair",R.drawable.ic_triceps_dip_on_chair,false,false)
         exercise.add(exeSeven)
         val exeEight=ExerciseList(8,"Plank",R.drawable.ic_plank,false,false)
         exercise.add(exeEight)
         val exeNine=ExerciseList(9,"High knees running in place",R.drawable.ic_high_knees_running_in_place,false,false)
         exercise.add(exeNine)
         val exeTen=ExerciseList(10,"Lunges",R.drawable.ic_lunge,false,false)
         exercise.add(exeTen)
         val exeEleven=ExerciseList(11,"Push Up and Rotation",R.drawable.ic_push_up_and_rotation,false,false)
         exercise.add(exeEleven)
         val exeTwelve=ExerciseList(12,"Side Plank",R.drawable.ic_side_plank,false,false)
         exercise.add(exeTwelve)
        return exercise
    }

}