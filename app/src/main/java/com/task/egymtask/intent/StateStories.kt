package com.task.egymtask.intent

import com.task.egymtask.model.data_model.TopStoriesModel

sealed class StateStories {
    object Idle : StateStories()
    object Loading : StateStories()
    data class Stories(val stories: TopStoriesModel) : StateStories()
    data class Error(val error: String?) : StateStories()
}