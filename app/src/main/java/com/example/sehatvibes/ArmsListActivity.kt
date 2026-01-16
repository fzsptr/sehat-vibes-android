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

class ArmsListActivity : AppCompatActivity() {

    private lateinit var rvArms: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_arms_list)

        initView()
        setupRecyclerView()
    }

    private fun initView() {
        rvArms = findViewById(R.id.rvArmsList)
    }

    private fun setupRecyclerView() {
        val workouts = getWorkoutData()

        rvArms.layoutManager = LinearLayoutManager(this)
        rvArms.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        rvArms.adapter = WorkoutAdapter(workouts) { workoutItem ->
            openYoutube(workoutItem.ytUrl)
        }
    }

    private fun openYoutube(url: String){
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }


    private fun getWorkoutData() : List<WorkoutItem> {
        return listOf(
            WorkoutItem(
                name = "Lingkar Lengan",
                duration = "00:30",
                calories = 2,
                ytUrl = "https://www.youtube.com/watch?v=h6GkzSA5tTc",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Peregangan Bahu",
                duration = "00:30",
                calories = 1,
                ytUrl = "https://www.youtube.com/watch?v=9k0EN2RCGgU",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Meninju",
                duration = "00:30",
                calories = 3,
                ytUrl = "https://www.youtube.com/watch?v=reeBHtZJ1ts",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Push Up Militer",
                duration = "x15",
                calories = 10,
                ytUrl = "https://www.youtube.com/watch?v=H8LoGZ-ZN48",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Curl Barbel Kaki Kiri",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=3kZS8HVFquk",
                calories = 5,
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Curl Barbel Kaki Kanan",
                duration = "x15",
                ytUrl = "https://youtu.be/3kZS8HVFquk?si=ik9uPiYqci3M6mwX",
                calories = 5,
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Angkat Trisep Di Lantai",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=geNkbcZ6qDo",
                calories = 3,
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Curl Pintu Kiri",
                duration = "x15",
                calories = 3,
                ytUrl = "https://www.youtube.com/watch?v=134v7cB-1W8",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Curl Pintu Kanan",
                duration = "x15",
                calories = 3,
                ytUrl = "https://www.youtube.com/watch?v=134v7cB-1W8",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Angkat Trisep Di Lantai",
                duration = "x15",
                calories = 3,
                ytUrl = "https://www.youtube.com/watch?v=geNkbcZ6qDo",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Gator Bahu",
                duration = "x16",
                calories = 3,
                ytUrl = "https://www.youtube.com/watch?v=JWp8_LGkTR8",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Peregangan Trisep Kiri",
                duration = "00:30",
                calories = 1,
                ytUrl = "https://www.youtube.com/watch?v=L9IGOcrdcFk",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Peregangan Trisep Kanan",
                duration = "00:30",
                calories = 1,
                ytUrl = "https://www.youtube.com/watch?v=L9IGOcrdcFk",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Peregangan Bisep Kiri",
                duration = "00:30",
                calories = 1,
                ytUrl = "https://youtu.be/jw8EXo5h0ec?si=lfBYr-fGlktZG9-N",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            ),
            WorkoutItem(
                name = "Peregangan Bisep Kanan",
                duration = "00:30",
                calories = 1,
                ytUrl = "https://youtu.be/jw8EXo5h0ec?si=tNdPde-h3CBTjXT7",
                iconRes = R.drawable.ic_item_arm,
                categories = "Strenght"
            )
        )

    }
}