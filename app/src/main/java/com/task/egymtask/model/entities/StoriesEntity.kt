package com.task.egymtask.model.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stories")
data class StoriesEntity(
    @PrimaryKey val title: String,
    val auther: String,
    val description: String,
    val image: String,
    val imageThumbnail: String,
    val url: String
)