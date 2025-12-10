package com.example.sehatvibes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.model.ProfileItem
import com.example.sehatvibes.R

class ProfileAdapter (
    private val list: List <ProfileItem>
) : RecyclerView.Adapter<ProfileAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle = view.findViewById<TextView>(R.id.txtTitle)
        val tvValue  = view.findViewById<TextView>(R.id.txtValue)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_profile_section, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.tvTitle.text = item.title
        holder.tvValue.text = item.value
    }

    override fun getItemCount() = list.size

}