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
import com.example.sehatvibes.adapter.WorkoutAdapter
import com.example.sehatvibes.item.WorkoutItem

class StomachListActivity : AppCompatActivity() {

    private lateinit var rvStomach: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_stomach_list)

        initView()
        setupRecyclerView()
    }

    private fun initView() {
        rvStomach = findViewById(R.id.rvStomachList)
    }

    private fun setupRecyclerView() {
        val workouts = getWorkoutData()

        rvStomach.layoutManager = LinearLayoutManager(this)
        rvStomach.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        rvStomach.adapter = WorkoutAdapter(workouts) { workoutItem ->
            openYoutube(workoutItem.ytUrl)
        }
    }

    private fun openYoutube(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun getWorkoutData() : List <WorkoutItem> {
        return listOf(
            WorkoutItem(
                name = "Loncat Bintang",
                duration = "00:30",
                ytUrl = "https://youtu.be/2W4ZNSwoW_4?si=fi7HoIYEVVDYOfq3"
            ),
            WorkoutItem(
                name = "Plank",
                duration = "01:00",
                ytUrl = "https://youtu.be/Fcbw82ykBvY?si=Kn_8IsgfcZdSjhI0"
            ),
            WorkoutItem(
                name = "Sit Up",
                duration = "x15",
                ytUrl = "https://youtu.be/swOyWKk7Oko?si=ksO-ix__opNjHE2p"
            ),
            WorkoutItem(
                name = "Crunch Perut",
                duration = "x15",
                ytUrl = "https://youtu.be/RUNrHkbP4Pc?si=cYComU3ZunDuV03s"
            ),
            WorkoutItem(
                name = "Sit Up V",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=5kvKmRGADlQ"
            ),
            WorkoutItem(
                name = "Crunch Sepeda",
                duration = "x15",
                ytUrl = "https://youtu.be/-nJkAJpQemI?si=9JqirPMxFt4W-Dwy"
            ),
            WorkoutItem(
                name = "Puntir Rusia",
                duration = "x20",
                ytUrl = "https://www.youtube.com/watch?v=DJQGX2J4IVw"
            ),
            WorkoutItem(
                name = "Pendaki Gunung",
                duration = "x20",
                ytUrl = "https://youtu.be/wQq3ybaLZeA?si=g6VpsMIgu9y0GR1g"
            ),
            WorkoutItem(
                name = "Angkat Kaki",
                duration = "x15",
                ytUrl = "https://youtu.be/dGKbTKLnym4?si=tRefXhbXuDnXjN2e"
            ),
            WorkoutItem(
                name = "Sentuh Tumit",
                duration = "x20",
                ytUrl = "https://youtu.be/9bR-elyolBQ?si=1kx786o9Fhqb5W6D"
            ),
            WorkoutItem(
                name = "Peregangan Kobra",
                duration = "01:00",
                ytUrl = "https://youtu.be/z21McHHOpAg?si=KRqS2myRQhoT4qEH"
            ),
            WorkoutItem(
                name = "Peregangan Puntir Lumbar Tulang Belakang",
                duration = "00:30",
                ytUrl = "https://youtu.be/ryNlb_0GmAw?si=CK0bW4DXyiDSZupY"
            ),
            WorkoutItem(
                name = "Peregangan Puntir Lumbar Tulang Belakang",
                duration = "00:30",
                ytUrl = "https://youtu.be/ryNlb_0GmAw?si=WS1uWLeGrsClFCbB"
            )
        )
    }
}