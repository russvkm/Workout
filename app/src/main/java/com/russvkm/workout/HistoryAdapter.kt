package com.russvkm.workout

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color.parseColor
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.history_adapter_layout.view.*

class HistoryAdapter(private val context: Context,private val arrayList:ArrayList<HistoryActivityDataClass>):RecyclerView.Adapter<HistoryAdapter.ViewHolder>() {
    class ViewHolder(view: View):RecyclerView.ViewHolder(view) {
        val dateAndTime: TextView =view.dateTimeTextView
        val countingTextView:TextView=view.serialNoTextView
        val container:LinearLayout=view.historyLinearLayout
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).
            inflate(R.layout.history_adapter_layout,
                parent,
                false)
        )
    }

    override fun getItemCount(): Int {
       return arrayList.size
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val data= arrayList[position]
       holder.dateAndTime.text=data.dateTime+"\n${data.timeDuration} " +
               "minutes of workout completed" +
               "\n${data.timeDuration*10} calories burned"

        holder.countingTextView.text=(position+1).toString()
        if(position%2==0){
            holder.container.setBackgroundColor(ContextCompat.getColor(context,R.color.white_gray))
        }else{
            holder.container.setBackgroundColor(parseColor("#ffffff"))

        }
    }
}