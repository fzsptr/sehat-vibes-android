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

class BackListActivity : AppCompatActivity() {

    private lateinit var rvBack: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_back_list)

        initView()
        setupRecyclerView()
    }

    private fun initView() {
        rvBack = findViewById(R.id.rvBackList)
    }

    private fun setupRecyclerView() {
        val workouts = getWorkoutData()

        rvBack.layoutManager = LinearLayoutManager(this)
        rvBack.addItemDecoration(
            DividerItemDecoration(this, DividerItemDecoration.VERTICAL)
        )
        rvBack.adapter = WorkoutAdapter(workouts) { workoutItem ->
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
                name = "Angkat Lengan ke Samping",
                duration = "x15",
                ytUrl = "https://youtu.be/Bqvmyni_sKQ?si=pM5LXmfXN18x6Oc_"
            ),
            WorkoutItem(
                name = "Tarikan Romboid",
                duration = "x12",
                ytUrl = "https://youtu.be/DEyDbzSudEU?si=TNHPIsVeG51Ic-rF"
            ),
            WorkoutItem(
                name = "Engsel Pinggul",
                duration = "x15",
                ytUrl = "https://youtu.be/VyFDPMOy-eA?si=9DYoaNKog4sBORUC"
            ),
            WorkoutItem(
                name = "Hiperektensi",
                duration = "x15",
                ytUrl = "https://youtu.be/W9y8xq4Ya_E?si=Wx7aYj7Q7oRGrOLD"
            ),
            WorkoutItem(
                name = "Malaikat Salju Terbalik",
                duration = "x15",
                ytUrl = "https://youtu.be/0qLP2RNKX4A?si=0Fi9TTQ5aoUagXmH"
            ),
            WorkoutItem(
                name = "Push Up Telentang",
                duration = "x10",
                ytUrl = "https://youtu.be/WwbgPb9Gb48?si=Vdk5XJqSk8cGJaQS"
            ),
            WorkoutItem(
                name = "Remasan Romboid Duduk",
                duration = "x12",
                ytUrl = "https://youtu.be/olv2Sv9DwmA?si=S1NKsfLEAxfGu4KG"
            ),
            WorkoutItem(
                name = "Superman dan Perenang",
                duration = "x12",
                ytUrl = "https://www.youtube.com/watch?v=pQUsUHvyoI0"
            ),
            WorkoutItem(
                name = "Tarikan Romboid",
                duration = "x12",
                ytUrl = "https://youtu.be/XydDDn_Rngw?si=d8xWKkseis-oU46a"
            ),
            WorkoutItem(
                name = "Peregangan Berbaring Miring Kiri",
                duration = "00:30",
                ytUrl = "https://youtu.be/DMlSdmsHEeI?si=7ZSg3tmjxuynLsq8"
            ),
            WorkoutItem(
                name = "Peregangan Berbaring Miring Kanan",
                duration = "00:30",
                ytUrl = "https://youtu.be/DMlSdmsHEeI?si=NEIgvZ1LlJjBcbLN"
            ),
            WorkoutItem(
                name = "Sikap Anak",
                duration = "00:30",
                ytUrl = "https://youtu.be/DMwRPGMPB10?si=I4nV-b6Qhv5F37OT"
            )
        )
    }
 }