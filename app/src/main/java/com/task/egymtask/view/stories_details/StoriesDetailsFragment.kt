package com.task.egymtask.view.stories_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.task.egymtask.R
import com.task.egymtask.databinding.FragmentStoriesDetailsBinding
import com.task.egymtask.model.data_model.TopStoriesModel
import com.task.egymtask.model.entities.StoriesEntity
import com.task.egymtask.utils.loadImage

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

        val arguments : StoriesEntity? = arguments?.getParcelable("details")

        binding.run {
            tvStoryTitle.text = arguments?.title
            tvStoryAuthor.text = arguments?.auther
            tvStoryDesc.text = arguments?.description
            arguments?.image?.let { ivStoryImage.loadImage(requireContext(), it) }
//            arguments?.multimedia?.find { it?.format == "Super Jumbo" }?.let { result ->
//                result.url?.let { ivStoryImage.loadImage(requireContext(), it) }
//            }
            tvSeeMore.setOnClickListener {
                val args = Bundle()
                args.putString("url", arguments?.url)
                findNavController().navigate(R.id.action_storiesDetailsFragment_to_webViewFragment2,args)
            }
        }
    }

}