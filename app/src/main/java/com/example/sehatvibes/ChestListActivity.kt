package com.example.sehatvibes

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.adapter.WorkoutAdapter
import com.example.sehatvibes.item.WorkoutItem

class ChestListActivity : AppCompatActivity() {

    private lateinit var rvChest: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_chest_list)

        initView()
        setupRecyclerView()
    }

    private fun initView() {
        rvChest = findViewById(R.id.rvChestList)
    }

    private fun setupRecyclerView() {
        val workouts = getWorkoutData()

        rvChest.layoutManager = LinearLayoutManager(this)
        rvChest.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        rvChest.adapter = WorkoutAdapter(workouts) { workoutItem ->
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
                ytUrl = "https://www.youtube.com/watch?v=h6GkzSA5tTc",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Loncat Bintang",
                duration = "00:30",
                ytUrl = "https://youtu.be/2W4ZNSwoW_4?si=fi7HoIYEVVDYOfq3",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Push Up",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=R08gYyypGto",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Push Up Berlian",
                duration = "x15",
                ytUrl = "https://youtu.be/UCmqw3kKZ38?si=z_MMg2yn1dINaE4Z",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Membalikkan Push Up",
                duration = "x10",
                ytUrl = "https://youtu.be/XRpbVcpx-Yc?si=ZXpb598jZk3hAPMY",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Push Up Dinding",
                duration = "x10",
                ytUrl = "https://youtu.be/EOf3cGIQpA4?si=M9Rgz8XHpqvagIBT",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Burpee",
                duration = "x10",
                ytUrl = "https://youtu.be/818SkLAPyKY?si=Z7bpYpP3bLE-VqNh",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Push Up Kaki Di Atas Bangku",
                duration = "x12",
                ytUrl = "https://youtu.be/OjPfLfLsw3c?si=zlbhk5CS-2o8eicj",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Push Up Tangan Melebar",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=pQUsUHvyoI0",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Push Up Lutut",
                duration = "x10",
                ytUrl = "https://youtu.be/jWxvty2KROs?si=qFd6zFk4XvAUrFlq",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Push Up dan Rotasi",
                duration = "x10",
                ytUrl = "https://youtu.be/Plv5CIclPtQ?si=p4wtav82jU9mjbT0",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Peregangan Bahu",
                duration = "00:30",
                ytUrl = "https://www.youtube.com/watch?v=9k0EN2RCGgU",
                iconRes = R.drawable.ic_item_upperbody
            ),
            WorkoutItem(
                name = "Peregangan Dada",
                duration = "00:30",
                ytUrl = "https://youtu.be/NS64IgKUyeY?si=Un_7HmsJZyYdmbgl",
                iconRes = R.drawable.ic_item_upperbody
            )
        )
    }
}