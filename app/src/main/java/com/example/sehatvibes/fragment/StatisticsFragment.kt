package com.example.sehatvibes.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.sehatvibes.HistoryActivity
import com.example.sehatvibes.R
import com.google.android.flexbox.FlexboxLayout
import java.time.LocalDate
import java.time.ZoneId
import java.time.format.TextStyle
import java.util.Locale

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [StatisticsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class StatisticsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_statistics, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupCalendar(view)

        val tvAllHistory = view.findViewById<TextView>(R.id.tvAllHistory)
        tvAllHistory.setOnClickListener {
            val intent = Intent(requireContext(), HistoryActivity::class.java)
            startActivity(intent)
        }
    }

    private fun setupCalendar(view: View) {

        val dayLayout = view.findViewById<FlexboxLayout>(R.id.layoutCalendarDays)
        val dateLayout = view.findViewById<FlexboxLayout>(R.id.layoutCalendarDates)

        dayLayout.removeAllViews()
        dateLayout.removeAllViews()

        val zoneId = ZoneId.systemDefault()
        val today = LocalDate.now(zoneId)

        // Mulai dari Hari Senin

        val startOfWeek = today.minusDays((today.dayOfWeek.value -1).toLong())
        val locale = Locale("id", "ID")
        for (i in 0..6) {
            val date = startOfWeek.plusDays(i.toLong())

            // Hari
            val dayText = TextView(requireContext()).apply {
                text = date.dayOfWeek.getDisplayName(TextStyle.SHORT, locale)
                textSize = 14f
                setTextColor(
                    if (date == today) 0xFF0F1115.toInt()
                    else  0xFF6B6B6B.toInt()
                )
                if (date == today ) setTypeface(null, android.graphics.Typeface.BOLD)
            }

            // Tanggal
            val dateText = TextView(requireContext()).apply {
                text = date.dayOfMonth.toString()
                textSize = 16f
                gravity = android.view.Gravity.CENTER
                setPadding(16, 16, 16, 16)

                if (date == today) {
                    setBackgroundResource(R.drawable.bg_circle_blue)
                    setTextColor(0xFF3B82F6.toInt())
                } else {
                    setTextColor(0xFF0F1115.toInt())
                }

                setOnClickListener {
                    // TODO: aksi ketika tanggal diklik
                    // contoh: load data statistik harian
                }
            }

            dayLayout.addView(dayText)
            dateLayout.addView(dateText)
        }



    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment StatisticsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            StatisticsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}