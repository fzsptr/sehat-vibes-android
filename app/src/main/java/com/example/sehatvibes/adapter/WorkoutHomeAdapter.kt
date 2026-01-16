package com.example.sehatvibes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.R
import com.example.sehatvibes.model.WorkoutHome

class WorkoutHomeAdapter(
    private var workouts: List<WorkoutHome>,
    private var onWorkoutClick: (WorkoutHome) -> Unit
) : RecyclerView.Adapter<WorkoutHomeAdapter.WorkoutViewHolder>() {

    inner class WorkoutViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardWorkout: CardView = itemView.findViewById(R.id.cardWorkout)
        val tvTitle: TextView = itemView.findViewById(R.id.tvWorkoutTitle)
        val tvDuration: TextView = itemView.findViewById(R.id.tvWorkoutDuration)
        val tvCalories: TextView = itemView.findViewById(R.id.tvWorkoutCalories)
        val btnStart: View = itemView.findViewById(R.id.btnStartWorkout)

        fun bind(workout: WorkoutHome) {
            tvTitle.text = workout.title
            tvDuration.text = workout.duration
            tvCalories.text = "${workout.calories} cal"

            // Set click listener on entire card
            cardWorkout.setOnClickListener {
                onWorkoutClick(workout)
            }

            // Set click listener on start button area
            btnStart.setOnClickListener {
                onWorkoutClick(workout)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkoutViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_workout_home, parent, false)
        return WorkoutViewHolder(view)
    }

    override fun onBindViewHolder(holder: WorkoutViewHolder, position: Int) {
        holder.bind(workouts[position])
    }

    override fun getItemCount() = workouts.size

    fun updateWorkouts(newWorkouts: List<WorkoutHome>) {
        workouts = newWorkouts
        notifyDataSetChanged()
    }
}