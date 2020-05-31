package com.russvkm.workout

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.exercise_counter_text_view.view.*

class ExerciseNumberAdapter(val exerciseList:ArrayList<ExerciseList>,val context:Context) :RecyclerView.Adapter<ExerciseNumberAdapter.ViewHolder>() {

    class ViewHolder(view: View):RecyclerView.ViewHolder(view){
        val textView: TextView =view.exerciseNumberHolderTextView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.exercise_counter_text_view,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return exerciseList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model:ExerciseList=exerciseList[position]
        holder.textView.text=model.id.toString()
        if(model.selected){
            holder.textView.background=ContextCompat.getDrawable(context,R.drawable.background_selected)
            holder.textView.setTextColor(Color.parseColor("#ffffff"))
        }else if(model.completed){
            holder.textView.background=ContextCompat.getDrawable(context,R.drawable.startup_button_background)
            holder.textView.setTextColor(Color.parseColor("#ffffff"))
        }else{
            holder.textView.background=ContextCompat.getDrawable(context,R.drawable.exercise_counter_text_view_color)
        }
    }
}