package com.example.sehatvibes.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import com.example.sehatvibes.ArmsListActivity
import com.example.sehatvibes.BackListActivity
import com.example.sehatvibes.ChestListActivity
import com.example.sehatvibes.LegListActivity
import com.example.sehatvibes.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ExploreFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ExploreFragment : Fragment() {
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
        return inflater.inflate(R.layout.fragment_explore, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val cardDada = view.findViewById<LinearLayout>(R.id.cardDada)
        val cardLengan = view.findViewById<LinearLayout>(R.id.cardLengan)
        val cardLeg = view.findViewById<LinearLayout>(R.id.cardLeg)
        val cardBack = view.findViewById<LinearLayout>(R.id.cardBack)

        cardDada.setOnClickListener {
            val intent = Intent(requireContext(), ChestListActivity::class.java)
            intent.putExtra("WORKOUT_TYPE", "DADA")
            startActivity(intent)
        }

        cardLengan.setOnClickListener {
            val intent = Intent(requireContext(), ArmsListActivity::class.java)
            intent.putExtra("WORKOUT_TYPE", "LENGAN")
            startActivity(intent)
        }

        cardLeg.setOnClickListener {
            val intent = Intent(requireContext(), LegListActivity::class.java)
            intent.putExtra("WORKOUT_TYPE", "KAKI")
            startActivity(intent)
        }

        cardBack.setOnClickListener {
            val intent = Intent(requireContext(), BackListActivity::class.java)
            intent.putExtra("WORKOUT_TYPE", "PUNGGUNG")
            startActivity(intent)
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ExploreFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            ExploreFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}