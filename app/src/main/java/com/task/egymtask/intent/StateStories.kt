package com.task.egymtask.intent

import com.task.egymtask.model.data_model.TopStoriesModel
import com.task.egymtask.model.entities.StoriesEntity

sealed class StateStories {
    object Idle : StateStories()
    object Loading : StateStories()
    data class Stories(val stories: List<StoriesEntity>) : StateStories()
    data class Error(val error: String?) : StateStories()
}