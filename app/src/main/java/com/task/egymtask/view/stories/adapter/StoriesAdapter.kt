package com.task.egymtask.view.stories.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.task.egymtask.databinding.ItemStoryBinding
import com.task.egymtask.model.data_model.TopStoriesModel
import com.task.egymtask.model.entities.StoriesEntity
import com.task.egymtask.utils.loadImage

class StoriesAdapter(
    private val answerList: List<StoriesEntity>,
    inline val onItemClicked: (StoriesEntity) -> Unit
) : RecyclerView.Adapter<StoriesAdapter.ViewHolder>() {

    lateinit var context: Context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context
        return ViewHolder(ItemStoryBinding.inflate(LayoutInflater.from(context), parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(context, answerList[position])
    }

    override fun getItemCount(): Int {
        return answerList.size
    }

    inner class ViewHolder(private val itemViewBinding: ItemStoryBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(context: Context, data: StoriesEntity) {
            itemViewBinding.run {
                tvStoryTitle.text = data.title
                tvStoryAuthor.text = data.auther
                data.imageThumbnail?.let { ivStoryImage.loadImage(context, it) }

//                data.multimedia?.find { it?.format == "Large Thumbnail" }?.let { result ->
//                    result.url?.let { ivStoryImage.loadImage(context, it) }
//                }
                root.setOnClickListener {
                    onItemClicked.invoke(data)
                }
            }
        }
    }
}