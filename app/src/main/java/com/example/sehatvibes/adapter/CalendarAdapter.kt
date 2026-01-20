package com.example.sehatvibes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.time.LocalDate
import com.example.sehatvibes.R
import java.time.ZoneId

class CalendarAdapter(
    private val dates: List<LocalDate?>,
    private val onDateClick: (LocalDate) -> Unit
) : RecyclerView.Adapter<CalendarAdapter.ViewHolder>() {

    private val today = LocalDate.now(ZoneId.systemDefault())

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvDate: TextView = itemView.findViewById(R.id.tvDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_calendar_date, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val date = dates[position]

        holder.tvDate.background = null
        holder.tvDate.text = ""
        holder.tvDate.setOnClickListener(null)

        if (date == null) return

        holder.tvDate.text = date.dayOfMonth.toString()

        if (date == today) {
            holder.tvDate.setBackgroundResource(R.drawable.bg_circle_blue)
            holder.tvDate.setTextColor(0xFF3B82F6.toInt())
        } else {
            holder.tvDate.setTextColor(0xFF0F1115.toInt())
        }

        holder.tvDate.setOnClickListener {
            onDateClick(date)
        }
    }
    override fun getItemCount() = dates.size
}