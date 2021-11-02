package com.example.demoprojectsealedcoroutinehilt.ui.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.demoprojectsealedcoroutinehilt.data.model.Post
import com.example.demoprojectsealedcoroutinehilt.databinding.ItemPostBinding
import com.example.demoprojectsealedcoroutinehilt.extensions.bindItem

class PostAdapter(context: Context) : RecyclerView.Adapter<PostAdapter.PostVH>() {

    private val inflate by lazy { LayoutInflater.from(context) }

    private var posts = mutableListOf<Post>()
    private lateinit var binding: ItemPostBinding

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostVH {
        binding = ItemPostBinding.inflate(inflate, parent, false)
        return PostVH(binding)

    }

    override fun onBindViewHolder(holder: PostVH, position: Int) = holder.bind()

    override fun getItemCount(): Int = posts.size

    fun submitPost(it: List<Post>) {
        posts.clear()
        posts.addAll(it)
        notifyItemRangeRemoved(0, it.size)
    }

    inner class PostVH(private var binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {
        init {
            itemView.apply {

            }
        }

        fun bind() = bindItem {
            binding.titlePost.text = posts[adapterPosition].title
            binding.bodyPost.text = posts[adapterPosition].body
        }
    }
}