package com.example.sehatvibes.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.R
import com.example.sehatvibes.adapter.CategoryAdapter
import com.example.sehatvibes.adapter.WorkoutHomeAdapter
import com.example.sehatvibes.model.DailyStats
import com.example.sehatvibes.model.WorkoutHome
import java.text.SimpleDateFormat
import java.util.*

class HomeFragment : Fragment() {

    // Views
    private lateinit var tvGreeting: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvCalories: TextView
    private lateinit var tvWorkouts: TextView
    private lateinit var tvMinutes: TextView
    private lateinit var tvStreak: TextView

    private lateinit var progressWorkoutBar: View
    private lateinit var progressCaloriesBar: View

    // RecyclerViews
    private lateinit var rvCategories: RecyclerView
    private lateinit var rvWorkouts: RecyclerView

    // Adapters
    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var workoutAdapter: WorkoutHomeAdapter

    // Data
    private var selectedCategory = "All"
    private val categories = listOf("All", "Strength", "Cardio", "Yoga", "HIIT")
    private lateinit var workouts: List<WorkoutHome>

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home, container, false)

        initializeViews(view)
        setupData()
        setupRecyclerViews()
        updateUI()

        return view
    }

    private fun initializeViews(view: View) {
        // Header
        tvGreeting = view.findViewById(R.id.tvGreeting)
        tvDate = view.findViewById(R.id.tvDate)

        // Today's Progress Stats
        tvCalories = view.findViewById(R.id.tvCalories)
        tvWorkouts = view.findViewById(R.id.tvWorkouts)
        tvMinutes = view.findViewById(R.id.tvMinutes)
        tvStreak = view.findViewById(R.id.tvStreak)

        // Week's Progress Stats
        progressWorkoutBar = view.findViewById(R.id.progressCaloriesBar)
        progressCaloriesBar = view.findViewById(R.id.progressWorkoutsBar)

        // RecyclerViews
        rvCategories = view.findViewById(R.id.rvCategories)
        rvWorkouts = view.findViewById(R.id.rvWorkouts)
    }

    private fun setupData() {
        workouts = listOf(
            WorkoutHome(
                id = 1,
                title = "Full Body Strength",
                duration = "30 min",
                level = "Intermediate",
                calories = 280,
                category = "Strength"
            ),
            WorkoutHome(
                id = 2,
                title = "Morning Yoga Flow",
                duration = "20 min",
                level = "Beginner",
                calories = 150,
                category = "Yoga"
            ),
            WorkoutHome(
                id = 3,
                title = "HIIT Cardio Blast",
                duration = "25 min",
                level = "Advanced",
                calories = 320,
                category = "HIIT"
            ),
            WorkoutHome(
                id = 4,
                title = "Core Strength",
                duration = "15 min",
                level = "Intermediate",
                calories = 180,
                category = "Strength"
            ),
            WorkoutHome(
                id = 5,
                title = "Evening Stretching",
                duration = "10 min",
                level = "Beginner",
                calories = 80,
                category = "Yoga"
            )
        )
    }

    private fun setupRecyclerViews() {
        // Categories RecyclerView
        categoryAdapter = CategoryAdapter(categories, selectedCategory) { category ->
            selectedCategory = category
            filterWorkouts(category)
        }

        rvCategories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
            setHasFixedSize(true)
        }

        // Workouts RecyclerView
        workoutAdapter = WorkoutHomeAdapter(workouts) { workout ->
            onWorkoutClicked(workout)
        }

        rvWorkouts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = workoutAdapter
            setHasFixedSize(true)
        }
    }

    private fun updateUI() {
        // Set greeting based on time
        val calendar = Calendar.getInstance()
        val hour = calendar.get(Calendar.HOUR_OF_DAY)

        val greeting = when (hour) {
            in 0..11 -> "Selamat Pagi"
            in 12..14 -> "Selamat Siang"
            in 15..18 -> "Selamat Sore"
            else -> "Selamat Malam"
        }

        tvGreeting.text = greeting

        // Set current date
        val dateFormat = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID"))
        tvDate.text = dateFormat.format(Date())

        // Set today's stats (example data - replace with actual data)
        val stats = DailyStats(
            calories = 420,
            workouts = 2,
            minutes = 45,
            streak = 7
        )

        tvCalories.text = stats.calories.toString()
        tvWorkouts.text = stats.workouts.toString()
        tvMinutes.text = stats.minutes.toString()
        tvStreak.text = stats.streak.toString()

        // Weekly achievement progress (example data)
        setProgressWeeks(progressCaloriesBar, 1850, 2000)
        setProgressWeeks(progressWorkoutBar, 11, 12)

    }

    private fun setProgressWeeks(bar: View, current: Int, max: Int) {
        bar.post {
            val parent = bar.parent as View
            val fullWidth = parent.width
            val progressWidth = (fullWidth * current) / max

            bar.layoutParams = bar.layoutParams.apply {
                width = progressWidth
            }
        }
    }

    private fun filterWorkouts(category: String) {
        val filteredWorkouts = if (category == "All") {
            workouts
        } else {
            workouts.filter { it.category == category }
        }

        workoutAdapter.updateWorkouts(filteredWorkouts)
        categoryAdapter.updateSelectedCategory(category)
    }

    private fun onWorkoutClicked(workout: WorkoutHome) {
        // Handle workout item click
        // Navigate to workout detail or start workout
        android.widget.Toast.makeText(
            context,
            "Starting ${workout.title}",
            android.widget.Toast.LENGTH_SHORT
        ).show()
    }

    companion object {
        @JvmStatic
        fun newInstance() = HomeFragment()
    }
}