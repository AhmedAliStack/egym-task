package com.task.egymtask.model.api

import com.task.egymtask.model.data_model.TopStoriesModel
import retrofit2.Response
import retrofit2.http.GET

const val Key : String = "Ces9AFncUYRAMvZ72kqugIWDYPThh3eu"

interface ApiService {

    @GET("home.json?api-key=$Key")
    suspend fun getStories(): Response<TopStoriesModel>

}