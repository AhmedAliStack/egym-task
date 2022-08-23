package com.task.egymtask.model.api
import com.google.common.truth.Truth.assertThat
import com.task.egymtask.api.FakeApiService
import com.task.egymtask.model.data_model.TopStoriesModel
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Before
import retrofit2.Response

class GeneralApiHelperImplTest{

    lateinit var fakeApiService: FakeApiService

    @Before
    fun setup() {
        fakeApiService = FakeApiService(Response.success(TopStoriesModel("test","",0, arrayListOf(),"","")))
    }

    @Test
    fun `Asset not Null`(){
        runBlocking {
            assertThat(fakeApiService.response.body() != null)
        }
    }

}