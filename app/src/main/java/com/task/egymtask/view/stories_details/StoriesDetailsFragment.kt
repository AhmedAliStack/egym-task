package com.task.egymtask.view.stories_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.task.egymtask.R

class StoriesDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = StoriesDetailsFragment()
    }

    private val viewModel: StoriesDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_stories_details, container, false)
    }


}