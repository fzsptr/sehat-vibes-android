package com.example.sehatvibes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.item.WorkoutItem
import com.example.sehatvibes.R
class WorkoutAdapter(
    private var list: List<WorkoutItem>,
    private val onItemClick: (WorkoutItem) -> Unit
) : RecyclerView.Adapter<WorkoutAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvDuration: TextView = view.findViewById(R.id.tvDuration)
        val tvCalories: TextView = view.findViewById(R.id.tvCalories)
        val imgIcon: ImageView = view.findViewById(R.id.ivItemWorkout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_section, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]

        holder.tvName.text = item.name
        holder.tvDuration.text = item.duration
        holder.tvCalories.text = "${item.calories} cal"
        holder.imgIcon.setImageResource(item.iconRes)

        holder.itemView.setOnClickListener {
            onItemClick(item)
        }
    }

    override fun getItemCount(): Int = list.size
}