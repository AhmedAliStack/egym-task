package com.task.egymtask.model

import androidx.room.Database
import androidx.room.RoomDatabase
import com.task.egymtask.model.entities.StoriesEntity


@Database(entities = [StoriesEntity::class], version = 1)
abstract class StoriesDatabase : RoomDatabase() {

    abstract fun storiesDao(): StoriesDao
}