package com.example.sehatvibes.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
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

    private lateinit var tvGreeting: TextView
    private lateinit var tvDate: TextView
    private lateinit var tvCalories: TextView
    private lateinit var tvWorkouts: TextView
    private lateinit var tvMinutes: TextView
    private lateinit var tvStreak: TextView

    private lateinit var progressCaloriesBar: View
    private lateinit var progressWorkoutBar: View

    private lateinit var rvCategories: RecyclerView
    private lateinit var rvWorkouts: RecyclerView

    private lateinit var categoryAdapter: CategoryAdapter
    private lateinit var workoutAdapter: WorkoutHomeAdapter

    private var selectedCategory = CATEGORY_ALL
    private val categories = listOf(
        CATEGORY_ALL, "Strength", "Cardio", "Yoga", "HIIT"
    )

    private val workouts: List<WorkoutHome> by lazy { provideWorkouts() }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false).also {
            bindViews(it)
            setupRecyclerViews()
            updateHeaderUI()
            updateTodayStats()
            updateWeeklyProgress()
        }
    }
    private fun bindViews(view: View) {
        tvGreeting = view.findViewById(R.id.tvGreeting)
        tvDate = view.findViewById(R.id.tvDate)
        tvCalories = view.findViewById(R.id.tvCalories)
        tvWorkouts = view.findViewById(R.id.tvWorkouts)
        tvMinutes = view.findViewById(R.id.tvMinutes)
        tvStreak = view.findViewById(R.id.tvStreak)

        progressCaloriesBar = view.findViewById(R.id.progressCaloriesBar)
        progressWorkoutBar = view.findViewById(R.id.progressWorkoutsBar)

        rvCategories = view.findViewById(R.id.rvCategories)
        rvWorkouts = view.findViewById(R.id.rvWorkouts)
    }

    private fun setupRecyclerViews() {
        setupCategoryRecycler()
        setupWorkoutRecycler()
    }

    private fun setupCategoryRecycler() {
        categoryAdapter = CategoryAdapter(categories, selectedCategory) { category ->
            selectedCategory = category
            filterWorkouts()
        }

        rvCategories.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            adapter = categoryAdapter
            setHasFixedSize(true)
        }
    }

    private fun setupWorkoutRecycler() {
        workoutAdapter = WorkoutHomeAdapter(workouts) { workout ->
            openYoutube(workout.ytUrl)
        }

        rvWorkouts.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = workoutAdapter
            setHasFixedSize(true)
        }
    }
    private fun updateHeaderUI() {
        tvGreeting.text = getGreeting()
        tvDate.text = getFormattedDate()
    }

    private fun updateTodayStats() {
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
    }

    private fun updateWeeklyProgress() {
        setProgress(progressCaloriesBar, 1850, 2000)
        setProgress(progressWorkoutBar, 11, 12)
    }

    private fun setProgress(bar: View, current: Int, max: Int) {
        bar.post {
            val parentWidth = (bar.parent as View).width
            bar.layoutParams = bar.layoutParams.apply {
                width = (parentWidth * current) / max
            }
        }
    }

    private fun filterWorkouts() {
        val filtered = if (selectedCategory == CATEGORY_ALL) {
            workouts
        } else {
            workouts.filter { it.category == selectedCategory }
        }

        workoutAdapter.updateWorkouts(filtered)
        categoryAdapter.updateSelectedCategory(selectedCategory)
    }

    private fun openYoutube(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun getGreeting(): String {
        return when (Calendar.getInstance().get(Calendar.HOUR_OF_DAY)) {
            in 0..11 -> "Selamat Pagi"
            in 12..14 -> "Selamat Siang"
            in 15..18 -> "Selamat Sore"
            else -> "Selamat Malam"
        }
    }

    private fun getFormattedDate(): String {
        val formatter = SimpleDateFormat("EEEE, d MMMM yyyy", Locale("id", "ID"))
        return formatter.format(Date())
    }
    private fun provideWorkouts(): List<WorkoutHome> = listOf(
        WorkoutHome(
            1, "Full Body Strength",
            "30 min", 280,
            "Strength",
            "https://youtu.be/UIPvIYsjfpo"
        ),
        WorkoutHome(
            2,
            "Morning Yoga Flow",
            "20 min",
            150,
            "Yoga",
            "https://youtu.be/CM43AZaRXNw"
        ),
        WorkoutHome(
            3,
            "HIIT Cardio Blast",
            "12 min", 300,
            "HIIT",
            "https://youtu.be/QTDbxTT8Pm8"
        ),
        WorkoutHome(4,
            "Core Strength",
            "7 min",
            90,
            "Strength",
            "https://youtu.be/_TdWdFQ1Cms"
        ),
        WorkoutHome(
            5,
            "Evening Stretching",
            "10 min",
            80,
            "Yoga",
            "https://youtu.be/9MzbRDm-A24"
        ),
        WorkoutHome(
            6,
            "Fat Burning Cardio",
            "20 min",
            200,
            "Cardio",
            "https://youtu.be/Pv6NrM7fqHY"
        )
    )

    companion object {
        private const val CATEGORY_ALL = "All"
    }
}