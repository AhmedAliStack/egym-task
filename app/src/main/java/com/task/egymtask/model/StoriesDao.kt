package com.task.egymtask.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.task.egymtask.model.data_model.TopStoriesModel
import com.task.egymtask.model.entities.StoriesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface StoriesDao {
    @Query("SELECT * FROM stories")
    fun getAllStories(): Flow<List<StoriesEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStory(stories: List<StoriesEntity>)

    @Query("DELETE FROM stories")
    suspend fun deleteAllStories()
}