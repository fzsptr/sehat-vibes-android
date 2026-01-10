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

class LegListActivity : AppCompatActivity() {

    private lateinit var rvLeg: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_leg_list)

        initView()
        setupRecyclerView()
    }

    private fun initView() {
        rvLeg = findViewById(R.id.rvLegList)
    }

    private fun setupRecyclerView() {
        val workouts = getWorkoutData()

        rvLeg.layoutManager = LinearLayoutManager(this)
        rvLeg.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        rvLeg.adapter = WorkoutAdapter(workouts) { workoutItem ->
            openYoutube(workoutItem.ytUrl)
        }
    }

    private fun openYoutube(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    private fun getWorkoutData() : List <WorkoutItem>  {
        return listOf(
            WorkoutItem(
                name = "Loncat Bintang",
                duration = "00:30",
                ytUrl = "https://youtu.be/2W4ZNSwoW_4?si=fi7HoIYEVVDYOfq3",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Squat",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=42bFodPahBU",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Burpee",
                duration = "x10",
                ytUrl = "https://youtu.be/818SkLAPyKY?si=Z7bpYpP3bLE-VqNh",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Lompat Jongkok",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=txLE-jOCEsc",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Duduk Di Dinding",
                duration = "01:00",
                ytUrl = "https://www.youtube.com/watch?v=Yp3ZwACK9v4",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Angkat Posisi Tandu Bersandar",
                duration = "x20",
                ytUrl = "https://www.youtube.com/watch?v=qQ-StR-AXzM",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Bungkukkan Badan",
                duration = "x20",
                ytUrl = "https://www.youtube.com/watch?v=-rTyKlHjYT8",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Lompat Jongkok",
                duration = "x15",
                ytUrl = "https://www.youtube.com/watch?v=txLE-jOCEsc",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Sisi Lingkaran Kaki Kiri",
                duration = "x12",
                ytUrl = "https://www.youtube.com/watch?v=VgysBPnVJWg",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Sisi Lingkaran Kaki Kanan",
                duration = "x12",
                ytUrl = "https://youtu.be/VgysBPnVJWg?si=wJ3c1pdmJFo1-MPO",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Peregangan Betis Kiri",
                duration = "00:30",
                ytUrl = "https://youtu.be/mJOGKTYUAzY?si=-4tlO2o4IUHyVAXC",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Peregangan Betis Kanan",
                duration = "00:30",
                ytUrl = "https://youtu.be/mJOGKTYUAzY?si=RxAnkpos_6ncL37M",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Peregangan Kuadrisep Kiri di Tembok",
                duration = "00:30",
                ytUrl = "https://youtu.be/TfcRyYf7WLg?si=Z1Gg55rWEINodbFy",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Peregangan Kuadrisep Kanan di Tembok",
                duration = "00:30",
                ytUrl = "https://youtu.be/TfcRyYf7WLg?si=On0HsNy6ilSy5pti",
                iconRes = R.drawable.ic_item_leg
            ),
            WorkoutItem(
                name = "Peregangan Kupu-Kupu Berbaring",
                duration = "00:30",
                ytUrl = "https://youtu.be/bzfY0Zr3sUE?si=H4I1ZWPQc0RNXDA",
                iconRes = R.drawable.ic_item_leg
            )
        )
    }

}