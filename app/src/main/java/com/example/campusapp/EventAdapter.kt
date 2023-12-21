package com.example.campusapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class EventAdapter(private val listData: ArrayList<event>): RecyclerView.Adapter<EventAdapter.DataViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.row_event, parent, false)
        return DataViewHolder(view)
    }

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) {
        val (event, tanggal, waktuevent, fee) = listData[position]
        holder.eventname.text = event
        holder.tanggalevent.text = tanggal
        holder.waktuevent.text = waktuevent
        holder.eventfee.text = fee
    }

    override fun getItemCount(): Int {
        return listData.count()
    }

    class DataViewHolder(item: View) : RecyclerView.ViewHolder(item){
        val eventname: TextView = item.findViewById(R.id.eventname)
        val tanggalevent: TextView = item.findViewById(R.id.tanggalevent)
        val waktuevent: TextView = item.findViewById(R.id.waktuevent)
        val eventfee: TextView = item.findViewById(R.id.eventfee)
    }

}