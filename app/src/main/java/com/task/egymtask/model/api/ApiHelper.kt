package com.task.egymtask.model.api

import com.task.egymtask.model.data_model.TopStoriesModel
import retrofit2.Response

interface ApiHelper {
    suspend fun storiesAsync(): Response<TopStoriesModel>
}