package com.example.sehatvibes.fragment

import android.animation.ValueAnimator
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import com.example.sehatvibes.R
import com.google.android.material.bottomsheet.BottomSheetDialog

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProfileFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProfileFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private var currentWeight = 57

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
        val view =  inflater.inflate(R.layout.fragment_profile, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuBtn = view.findViewById<ImageView>(R.id.imgMenu)
        val weightContainer: FrameLayout = view.findViewById(R.id.weightContainer)
        val tvWeight : TextView = view.findViewById(R.id.tvWeightValue)
        val tvLongestStreak = view.findViewById<TextView>(R.id.tvLongestStreak)
        val streakProgress : ProgressBar = view.findViewById(R.id.streakProgress)

        val longestStreak = 14
        val streakGoal = 30

        menuBtn.setOnClickListener {
            showBottomSheetMenu()
        }

        weightContainer.setOnClickListener {
            showEditWeightBottomSheet(tvWeight)
        }

        animateStreakCount(tvLongestStreak, longestStreak)

        // Progress ring
        streakProgress.progress =
            ((longestStreak.toFloat() / streakGoal) * 100).toInt()

        tvLongestStreak.performHapticFeedback(
            android.view.HapticFeedbackConstants.KEYBOARD_TAP
        )

    }

    private fun showBottomSheetMenu() {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(
            R.layout.bottom_sheet_menu,
            null
        )

        val menuShare = view.findViewById<View>(R.id.menuShare)

        menuShare.setOnClickListener {
            dialog.dismiss()
            shareToFriend()
        }

        dialog.setContentView(view)
        dialog.show()
    }

    private fun shareToFriend() {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(
            Intent.EXTRA_TEXT,
            "Ayo latihan bareng! Download aplikasi SehatVibes sekarang!"
        )

        startActivity(Intent.createChooser(intent, "Bagikan ke teman"))
    }

    private fun showEditWeightBottomSheet(tvWeight: TextView) {
        val dialog = BottomSheetDialog(requireContext())
        val view = layoutInflater.inflate(
            R.layout.bottom_sheet_weight,
            null
        )

        val seekBar = view.findViewById<SeekBar>(R.id.seekWeight)
        val preview = view.findViewById<TextView>(R.id.tvWeightPreview)

        seekBar.progress = currentWeight
        preview.text = currentWeight.toString()

        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar?, progress: Int, fromUser: Boolean) {
                preview.text = progress.toString()
            }

            override fun onStopTrackingTouch(sb: SeekBar?) {
                sb?.progress?.let {
                    animateWeightChange(tvWeight, currentWeight, it)
                    currentWeight = it
                }
                dialog.dismiss()
            }

            override fun onStartTrackingTouch(sb: SeekBar?) {}
        })

        dialog.setContentView(view)
        dialog.show()
    }

    private fun animateWeightChange(
        textView: TextView,
        from: Int,
        to: Int
    ) {
        val animator = ValueAnimator.ofInt(from, to)
        animator.duration = 400
        animator.interpolator = DecelerateInterpolator()

        animator.addUpdateListener {
            textView.text = it.animatedValue.toString()
        }
        animator.start()
    }

    private fun animateStreakCount(
        textView: TextView,
        targetValue: Int,
        duration: Long = 900
    ) {
        val animator = ValueAnimator.ofInt(0, targetValue)
        animator.duration = duration
        animator.interpolator = DecelerateInterpolator()

        animator.addUpdateListener {
            textView.text = it.animatedValue.toString()
        }

        animator.start()

        textView.animate()
            .scaleX(1.08f)
            .scaleY(1.08f)
            .setDuration(120)
            .withEndAction {
                textView.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .duration = 120
            }

    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProfileFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ProfileFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}