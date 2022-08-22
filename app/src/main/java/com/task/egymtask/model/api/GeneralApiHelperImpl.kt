package com.task.egymtask.model.api

import com.task.egymtask.model.data_model.TopStoriesModel
import retrofit2.Response

class GeneralApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun storiesAsync(): Response<TopStoriesModel> {
        return apiService.getStories()
    }

}