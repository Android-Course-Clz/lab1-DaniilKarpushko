package com.example.newmobileapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

class ProfileActivity : ComponentActivity() {
    private var followers = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.profile)

        val avatarImageView: ImageView = findViewById(R.id.imageAvatarView)

        val avatarUrl = "https://randomuser.me/api/portraits/men/1.jpg"

        Glide.with(this)
            .load(avatarUrl)
            .placeholder(R.drawable.ic_profile_placeholder)
            .error(R.drawable.ic_profile_placeholder)
            .circleCrop()
            .into(avatarImageView)

        val recyclerView: RecyclerView = findViewById(R.id.lt_posts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val posts = mutableListOf(
            Post(1,"Первый пост!", "https://via.placeholder.com/150", 0, 0),
            Post(2,"Второй пост!", "https://via.placeholder.com/150", 0, 0),
            Post(3,"Третий пост!", "https://via.placeholder.com/150", 0, 0)
        )

        recyclerView.adapter = PostAdapter(posts)

        val postsTextView: TextView = findViewById(R.id.tv_postsCount)
        postsTextView.text = "Постов ${posts.size}"


        val followersTextView: TextView = findViewById(R.id.tv_followersCount)
        val followButton: Button = findViewById(R.id.btn_follow)

        followersTextView.text = "Подписчики $followers"

        followButton.setOnClickListener {
            followers++
            followersTextView.text = "Подписчики $followers"
        }


    }
}