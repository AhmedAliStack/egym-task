package com.task.egymtask.api

import com.task.egymtask.model.api.ApiService
import com.task.egymtask.model.data_model.TopStoriesModel
import retrofit2.Response

class FakeApiService constructor(val response: Response<TopStoriesModel>):ApiService {
    override suspend fun getStories(): Response<TopStoriesModel> {
        System.out.println("Hello StackOverflow")
        return Response.success(TopStoriesModel("","",0, arrayListOf(),"",""))
    }
}