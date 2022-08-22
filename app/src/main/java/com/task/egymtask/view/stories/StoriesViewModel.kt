package com.task.egymtask.view.stories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.JsonParser
import com.task.egymtask.intent.IntentStories
import com.task.egymtask.intent.StateStories
import com.task.egymtask.model.repo.GeneralRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(private val generalRepo: GeneralRepo): ViewModel() {
    var intentStories = Channel<IntentStories>()
    private val _state = MutableStateFlow<StateStories>(StateStories.Idle)
    val state: StateFlow<StateStories> get() = _state

    init {
        fetchStories()
    }

    private fun fetchStories() {
        val handler = CoroutineExceptionHandler { _, exception ->
            _state.value = StateStories.Error(exception.localizedMessage)
        }

        viewModelScope.launch(handler) {
            _state.value = StateStories.Loading
            val response = generalRepo.questions()
            if (response != null)
                _state.value = StateStories.Stories(
                    response
                )
            else {
                _state.value = StateStories.Error("Something went wrong")
            }
        }
    }
}