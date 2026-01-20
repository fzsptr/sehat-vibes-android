package com.example.sehatvibes

import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.adapter.CalendarAdapter
import com.example.sehatvibes.adapter.CalendarDayAdapter
import com.example.sehatvibes.utils.CalendarDays
import java.time.YearMonth
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.util.Locale

class HistoryActivity : AppCompatActivity() {

    private var currentMonth = YearMonth.now(ZoneId.systemDefault())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)

        val root = findViewById<LinearLayout>(R.id.rootLayout)

        ViewCompat.setOnApplyWindowInsetsListener(root) { view, insets ->
            val statusBarHeight = insets.getInsets(WindowInsetsCompat.Type.statusBars()).top
            view.setPadding(
                view.paddingLeft,
                statusBarHeight,
                view.paddingRight,
                view.paddingBottom
            )
            insets
        }

        backToStatistics()
        setupCalendar()
        setupNavigation()
    }

    private fun backToStatistics() {
        val btnBack = findViewById<ImageView>(R.id.btnBack)
        btnBack.setOnClickListener {
            finish()
        }
    }

    private fun setupCalendar() {
        val formatter = DateTimeFormatter.ofPattern("MMMM yyyy", Locale("id","ID"))
        findViewById<TextView>(R.id.tvMonthYear).text =
            currentMonth.format(formatter)

        val days = CalendarDays.generateCalendarDays(currentMonth)

        val rvCalendar = findViewById<RecyclerView>(R.id.rvCalendar)
        rvCalendar.layoutManager = GridLayoutManager(this, 7)
        rvCalendar.adapter = CalendarAdapter(days) { selectedDate ->

        }
        val rvDays = findViewById<RecyclerView>(R.id.rvDays)
        rvDays.layoutManager = GridLayoutManager(this, 7)
        rvDays.adapter = CalendarDayAdapter(
            listOf("Sen", "Sel", "Rab", "Kam", "Jum", "Sab", "Min")
        )
        rvCalendar.setHasFixedSize(true)
        rvDays.setHasFixedSize(true)
    }

    private fun setupNavigation() {
        findViewById<ImageView>(R.id.btnPrev).setOnClickListener {
            currentMonth = currentMonth.minusMonths(1)
            setupCalendar()
        }

        findViewById<ImageView>(R.id.btnNext).setOnClickListener {
            currentMonth = currentMonth.plusMonths(1)
            setupCalendar()
        }
    }
}