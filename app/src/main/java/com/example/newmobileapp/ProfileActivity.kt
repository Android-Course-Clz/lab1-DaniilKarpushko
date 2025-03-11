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
        val avatarUrl =
            "https://avatars.mds.yandex.net/i?id=40b16ad632292cb7c9bd76a4384c864a_sr-10120057-images-thumbs&n=13"
        Glide.with(this)
            .load(avatarUrl)
            .placeholder(R.drawable.ic_profile_placeholder)
            .error(R.drawable.ic_profile_placeholder)
            .circleCrop()
            .into(avatarImageView)

        val recyclerView: RecyclerView = findViewById(R.id.lt_posts)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val posts = mutableListOf(
            Post(
                1,
                "Первый пост",
                "https://avatars.mds.yandex.net/i?id=8ffed75590876f97a877fc58f850d79d53c517cc-5336554-images-thumbs&n=13",
                0,
                0
            ),
            Post(
                2,
                "Второй пост",
                "https://steamuserimages-a.akamaihd.net/ugc/52453354080448818/543783B601D5A853E3F50907B9722A314DFD92B6/?imw=512&amp;imh=320&amp;ima=fit&amp;impolicy=Letterbox&amp;imcolor=%23000000&amp;letterbox=true",
                0,
                0
            ),
            Post(3, "Третий пост", "", 0, 0)
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