package com.example.sehatvibes.item

data class WorkoutItem(
    val name: String,
    val duration: String,
    val calories: Int,
    val ytUrl: String,
    val iconRes: Int,
    val categories: String
)