package com.example.newmobileapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class PostAdapter(private var posts: List<Post>) :
    RecyclerView.Adapter<PostAdapter.PostViewHolder>() {

    class PostViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val postText: TextView = view.findViewById(R.id.post_text)
        val postImage: ImageView = view.findViewById(R.id.post_image)
        val likeCount: TextView = view.findViewById(R.id.like_count)
        val commentCount: TextView = view.findViewById(R.id.comment_count)
        val likeButton: ImageButton = view.findViewById(R.id.btn_like)
        val commentButton: ImageButton = view.findViewById(R.id.btn_comment)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.post_layout, parent, false)
        return PostViewHolder(view)
    }

    override fun getItemCount(): Int = posts.size

    override fun onBindViewHolder(holder: PostViewHolder, position: Int) {
        val post = posts[position]

        holder.postText.text = post.text
        holder.likeCount.text = "${post.likeCount} лайков"
        holder.commentCount.text = "${post.commentCount} комментариев"

        if (!post.imageUrl.isNullOrEmpty()) {
            Glide.with(holder.itemView.context)
                .load(post.imageUrl)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(holder.postImage)
            holder.postImage.visibility = View.VISIBLE
        } else {
            holder.postImage.visibility = View.GONE
        }

        holder.likeButton.setOnClickListener {
            val updatedPost = post.copy(likeCount = post.likeCount + 1)
            updatePost(updatedPost)
        }

        holder.commentButton.setOnClickListener {
            val updatedPost = post.copy(commentCount = post.commentCount + 1)
            updatePost(updatedPost)
        }
    }

    private fun updatePost(updatedPost: Post) {
        val index = posts.indexOfFirst { it.id == updatedPost.id }
        if (index != -1) {
            val newPosts = posts.toMutableList()
            newPosts[index] = updatedPost
            setPosts(newPosts)
        }
    }

    private fun setPosts(newList: List<Post>) {
        val diffCallback = PostDiffCallback(posts, newList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        posts = newList
        diffResult.dispatchUpdatesTo(this)
    }
}
