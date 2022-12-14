package com.task.egymtask.view.stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.task.egymtask.R
import com.task.egymtask.databinding.FragmentStoriesBinding
import com.task.egymtask.intent.IntentStories
import com.task.egymtask.intent.StateStories
import com.task.egymtask.model.data_model.TopStoriesModel
import com.task.egymtask.model.entities.StoriesEntity
import com.task.egymtask.view.stories.adapter.StoriesAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class StoriesFragment : Fragment() {

    private val viewModel: StoriesViewModel by viewModels()
    lateinit var binding: FragmentStoriesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentStoriesBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        startViewModel()
        observeViewModel()
    }

    private fun startViewModel() {
        lifecycleScope.launch {
            viewModel.intentStories.send(IntentStories.FetchStories)
        }
    }

    private fun observeViewModel() {
        lifecycleScope.launchWhenStarted {
            viewModel.state.collectLatest {
                when (it) {
                    is StateStories.Error -> {
                        handleLoading(isLoading = false)
                        Toast.makeText(requireContext(), it.error, Toast.LENGTH_LONG).show()
                    }
                    StateStories.Idle -> {

                    }
                    StateStories.Loading -> {
                        handleLoading(isLoading = true)
                    }
                    is StateStories.Stories -> {
                        handleLoading(isLoading = false)
                        handleData(it.stories)
                    }
                }
            }
        }
    }

    private fun handleData(stories: List<StoriesEntity>) {
        handleEmpty(stories)
        binding.run {
            StoriesAdapter(stories) { result ->
                val args = Bundle()
                args.putParcelable("details", result)
                args.putString("title", result.title)
                findNavController().navigate(
                    R.id.action_storiesFragment_to_storiesDetailsFragment,
                    args
                )
            }.also { adapter ->
                rvStories.adapter = adapter
                rvStories.layoutManager = LinearLayoutManager(requireContext())
                adapter.notifyItemChanged(stories.size)
            }
        }
    }

    private fun handleEmpty(stories: List<StoriesEntity>) {
        binding.run {
            rvStories.isVisible = stories.isNotEmpty()
            tvNoData.isVisible = stories.isEmpty()
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        binding.run {
            pbLoading.isVisible = isLoading
            rvStories.isVisible = !isLoading
        }
    }
}