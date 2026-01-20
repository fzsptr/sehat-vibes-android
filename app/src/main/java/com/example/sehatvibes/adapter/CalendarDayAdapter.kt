package com.example.sehatvibes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.R

class CalendarDayAdapter(
    private val days: List<String>
) : RecyclerView.Adapter<CalendarDayAdapter.ViewHolder>() {

    inner class ViewHolder(val tv: TextView) :
        RecyclerView.ViewHolder(tv)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_day, parent, false) as TextView
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.tv.text = days[position]
    }

    override fun getItemCount() = days.size
}