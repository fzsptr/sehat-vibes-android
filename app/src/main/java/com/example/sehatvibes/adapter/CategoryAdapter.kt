package com.example.sehatvibes.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.sehatvibes.R

class CategoryAdapter (
    private val categories: List<String>,
    private var selectedCategory: String,
    private val onCategoryClick: (String) -> Unit
) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {

    inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardCategory: CardView = itemView.findViewById(R.id.cardCategory)
        val tvCategory: TextView = itemView.findViewById(R.id.tvCategory)

        fun bind(category: String, isSelected: Boolean) {
            tvCategory.text = category

            if(isSelected) {
                cardCategory.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.black)
                )
                tvCategory.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.white)
                )
            } else {
                cardCategory.setCardBackgroundColor(
                    ContextCompat.getColor(itemView.context, R.color.white)
                )
                tvCategory.setTextColor(
                    ContextCompat.getColor(itemView.context, R.color.black)
                )
            }
            cardCategory.setOnClickListener {
                onCategoryClick(category)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_category, parent, false)
        return CategoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        val category = categories[position]
        holder.bind(category, category == selectedCategory)
    }

    override fun getItemCount() = categories.size

    fun updateSelectedCategory(category: String) {
        selectedCategory = category
        notifyDataSetChanged()
    }
}