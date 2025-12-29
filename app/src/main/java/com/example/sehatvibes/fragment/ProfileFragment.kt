package com.example.sehatvibes.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.example.sehatvibes.R
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.adapter.ProfileAdapter
import com.example.sehatvibes.item.ProfileItem
import com.example.sehatvibes.lib.ApiConfig
import com.example.sehatvibes.model.ResponseError
import com.example.sehatvibes.utils.DateFormatter
import com.google.gson.Gson
import kotlinx.coroutines.launch

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

        val recyclerView = view.findViewById<RecyclerView>(R.id.rvProfile)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        val adapter = ProfileAdapter(mutableListOf())
        recyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val menuBtn = view.findViewById<ImageView>(R.id.imgMenu)
        val tvName = view.findViewById<TextView>(R.id.tvName)

        menuBtn.setOnClickListener {
            showPopupMenu(it)
        }

        get(tvName)
    }

    private fun showPopupMenu(anchor: View) {
        val popup = PopupMenu(requireContext(), anchor)
        popup.menuInflater.inflate(R.menu.profile_menu, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.actionShare -> {
                    shareToFriend()
                    true
                }

                else -> false
            }
        }

        popup.show()
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

    private fun get(tvName: TextView) {
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val api = ApiConfig.getAuthApi(requireContext())
                val response = api.get()

                if(response.isSuccessful) {
                    val body = response.body()
                    val user = body?.data

                    if(user != null) {
                        tvName.text = user.name

                        val profileList = mutableListOf(
                            ProfileItem("Nama", user.name),
                            ProfileItem("Berat Badan", "${user.weight} kg"),
                            ProfileItem("Member Sejak", DateFormatter.formatToDate(user.createdAt))
                        )

                        val recyclerView = requireView().findViewById<RecyclerView>(R.id.rvProfile)
                        recyclerView.adapter = ProfileAdapter(profileList)
                    }
                } else {
                    val errorBody = response.errorBody()?.string()
                    val error = Gson().fromJson(errorBody, ResponseError::class.java)
                    Toast.makeText(requireContext(), error.message, Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(requireContext(), e.message ?: "Gagal mengambil data user", Toast.LENGTH_SHORT).show()
            }
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