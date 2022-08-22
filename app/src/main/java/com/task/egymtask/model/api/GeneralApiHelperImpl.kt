package com.task.egymtask.model.api

import com.task.egymtask.model.StoriesDatabase
import com.task.egymtask.model.data_model.TopStoriesModel
import com.task.egymtask.model.entities.StoriesEntity
import retrofit2.Response
import javax.inject.Inject

class GeneralApiHelperImpl(private val apiService: ApiService,@Inject private val db: StoriesDatabase) : ApiHelper {

    private val storiesDao = db.storiesDao()

    override suspend fun storiesAsync(): TopStoriesModel? {

        return apiService.getStories().body()

    }

}