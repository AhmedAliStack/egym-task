package com.task.egymtask.view.stories

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.task.egymtask.intent.IntentStories
import com.task.egymtask.intent.StateStories
import com.task.egymtask.model.api.GeneralApiHelperImpl
import com.task.egymtask.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.consumeEach
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StoriesViewModel @Inject constructor(private val generalRepo: GeneralApiHelperImpl): ViewModel() {
    var intentStories = Channel<IntentStories>()
    private val _state = MutableStateFlow<StateStories>(StateStories.Idle)
    val state: StateFlow<StateStories> get() = _state

    init {
        handleIntent()
    }

    private fun handleIntent() {
        viewModelScope.launch {
            intentStories.consumeEach {
                when (it) {
                    is IntentStories.FetchStories -> {
                        fetchStories()
                    }
                }
            }
        }
    }

    private fun fetchStories() {
        val handler = CoroutineExceptionHandler { _, exception ->
            _state.value = StateStories.Error(exception.localizedMessage)
        }

        viewModelScope.launch(handler) {
            generalRepo.getStories().collectLatest { resource ->
                resource.data?.let {
                    _state.value = StateStories.Stories(
                        it
                    )
                }
                when(resource){
                    is Resource.Success -> {

                    }
                    is Resource.Error -> {
                        _state.value = StateStories.Error("Something went wrong")
                    }
                    is Resource.Loading ->
                        _state.value = StateStories.Loading

                }
            }
        }
    }
}