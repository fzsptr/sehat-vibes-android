package com.example.sehatvibes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.adapter.ChestAdapter
import com.example.sehatvibes.item.WorkoutItem

class ChestListActivity : AppCompatActivity() {

    private lateinit var rvWorkout: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chest_list)

        initView()
        setupRecyclerView()
    }

    private fun initView() {
        rvWorkout = findViewById(R.id.rvChestList)
    }

    private fun setupRecyclerView() {
        val workouts = getWorkoutData()

        rvWorkout.layoutManager = LinearLayoutManager(this)
        rvWorkout.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        rvWorkout.adapter = ChestAdapter(workouts) { workoutItem ->
            openYoutube(workoutItem.ytUrl)
        }
    }

    private fun openYoutube(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun getWorkoutData() : List<WorkoutItem> {
        return listOf(
            WorkoutItem(
                name = "Lingkar Lengan",
                duration = "00:30",
                ytUrl = "https://www.youtube.com/watch?v=h6GkzSA5tTc"
            ),
            WorkoutItem(
                name = "Loncat Bintang",
                duration = "00:30",
                ytUrl = "https://youtu.be/2W4ZNSwoW_4?si=fi7HoIYEVVDYOfq3"
            ),
            WorkoutItem(
                name = "Push Up",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=R08gYyypGto"
            ),
            WorkoutItem(
                name = "Push Up Berlian",
                duration = "x15",
                ytUrl = "https://youtu.be/UCmqw3kKZ38?si=z_MMg2yn1dINaE4Z"
            ),
            WorkoutItem(
                name = "Membalikkan Push Up",
                duration = "x10",
                ytUrl = "https://youtu.be/XRpbVcpx-Yc?si=ZXpb598jZk3hAPMY"
            ),
            WorkoutItem(
                name = "Push Up Dinding",
                duration = "x10",
                ytUrl = "https://youtu.be/EOf3cGIQpA4?si=M9Rgz8XHpqvagIBT"
            ),
            WorkoutItem(
                name = "Burpee",
                duration = "x10",
                ytUrl = "https://youtu.be/818SkLAPyKY?si=Z7bpYpP3bLE-VqNh"
            ),
            WorkoutItem(
                name = "Push Up Kaki Di Atas Bangku",
                duration = "x12",
                ytUrl = "https://youtu.be/OjPfLfLsw3c?si=zlbhk5CS-2o8eicj"
            ),
            WorkoutItem(
                name = "Push Up Tangan Melebar",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=pQUsUHvyoI0"
            ),
            WorkoutItem(
                name = "Push Up Lutut",
                duration = "x10",
                ytUrl = "https://youtu.be/jWxvty2KROs?si=qFd6zFk4XvAUrFlq"
            ),
            WorkoutItem(
                name = "Push Up dan Rotasi",
                duration = "x10",
                ytUrl = "https://youtu.be/Plv5CIclPtQ?si=p4wtav82jU9mjbT0"
            ),
            WorkoutItem(
                name = "Peregangan Bahu",
                duration = "00:30",
                ytUrl = "https://www.youtube.com/watch?v=9k0EN2RCGgU"
            ),
            WorkoutItem(
                name = "Peregangan Dada",
                duration = "00:30",
                ytUrl = "https://youtu.be/NS64IgKUyeY?si=Un_7HmsJZyYdmbgl"
            )
        )
    }
}