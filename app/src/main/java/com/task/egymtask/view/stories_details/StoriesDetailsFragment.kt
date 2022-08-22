package com.task.egymtask.view.stories_details

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import com.task.egymtask.R
import com.task.egymtask.databinding.FragmentStoriesDetailsBinding
import com.task.egymtask.view.MainActivity

class StoriesDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = StoriesDetailsFragment()
    }

    private val viewModel: StoriesDetailsViewModel by viewModels()
    lateinit var binding: FragmentStoriesDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentStoriesDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

}