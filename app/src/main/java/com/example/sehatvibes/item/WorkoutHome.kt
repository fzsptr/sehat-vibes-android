package com.example.sehatvibes.model

data class WorkoutHome(
    val id: Int,
    val title: String,
    val duration: String,
    val level: String,
    val calories: Int,
    val imageEmoji: String,
    val category: String,
    val description: String = ""
)

data class DailyStats(
    val calories: Int,
    val workouts: Int,
    val minutes: Int,
    val streak: Int
)

data class Category(
    val name: String,
    var isSelected: Boolean = false
)