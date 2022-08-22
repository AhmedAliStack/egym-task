package com.task.egymtask.model.repo

import com.task.egymtask.model.api.ApiHelper

class GeneralRepo(private val apiHelper: ApiHelper) {
    suspend fun questions() = apiHelper.storiesAsync()
}