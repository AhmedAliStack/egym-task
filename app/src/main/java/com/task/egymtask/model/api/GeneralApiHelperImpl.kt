package com.task.egymtask.model.api

import androidx.room.withTransaction
import com.task.egymtask.model.StoriesDatabase
import com.task.egymtask.model.data_model.TopStoriesModel
import com.task.egymtask.model.entities.StoriesEntity
import com.task.egymtask.utils.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class GeneralApiHelperImpl @Inject constructor(val apiService: ApiService, val db: StoriesDatabase) {

    private val storiesDao = db.storiesDao()

    fun mapStories(body: TopStoriesModel?): ArrayList<StoriesEntity> {
        val result:ArrayList<StoriesEntity> = arrayListOf()
        body?.results?.forEach {
            result.add(StoriesEntity(it.title ?: "",it.byline,it.abstract,
                it.multimedia?.get(0)?.url, it.multimedia?.get(2)?.url,it.url))
        }
        return result
    }

    fun getRestaurants() = networkBoundResource(
        query = {
            storiesDao.getAllStories()
        },
        fetch = {
            delay(2000)
            apiService.getStories()
        },
        saveFetchResult = { restaurants ->
            db.withTransaction {
                storiesDao.deleteAllStories()
                val result = mapStories(apiService.getStories().body())
                storiesDao.insertStory(result)
            }
        }
    )


}