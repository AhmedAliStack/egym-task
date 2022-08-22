package com.task.egymtask.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.egymtask.model.data_model.TopStoriesModel
import kotlinx.coroutines.flow.Flow

@Dao
interface StoriesDao {
    @Query("SELECT * FROM stories")
    fun getAllStories(): List<TopStoriesModel.Result>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(stories: List<TopStoriesModel.Result>)

    @Query("DELETE FROM stories")
    suspend fun deleteAllStories()
}