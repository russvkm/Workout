package com.russvkm.workout

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_history.*

class History : AppCompatActivity() {
    private var recyclerViewAdapter:HistoryAdapter?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)
        conFiguringActionBar()
        configuringRecyclerView()
    }

    private fun conFiguringActionBar(){
        setSupportActionBar(historyToolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        historyToolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun configuringRecyclerView(){
        historyRecyclerView.layoutManager=LinearLayoutManager(this)
        val db=DataBaseHelper(this)
        if(db.readData().size>0){
            historyRecyclerView.visibility= View.VISIBLE
            noDataTextView.visibility=View.GONE
            recyclerViewAdapter=HistoryAdapter(this@History,db.readData())
            historyRecyclerView.adapter=recyclerViewAdapter
        }else{
            historyRecyclerView.visibility= View.GONE
            noDataTextView.visibility=View.VISIBLE
        }
    }
}
