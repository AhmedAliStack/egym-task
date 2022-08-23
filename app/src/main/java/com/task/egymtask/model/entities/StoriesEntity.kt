package com.task.egymtask.model.entities

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "stories")
@Parcelize
data class StoriesEntity(
    @PrimaryKey val title: String,
    val auther: String?,
    val description: String?,
    val image: String?,
    val imageThumbnail: String?,
    val url: String?
):Parcelable