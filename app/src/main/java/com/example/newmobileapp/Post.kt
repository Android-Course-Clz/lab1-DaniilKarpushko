package com.example.newmobileapp

data class Post(
    val id: Int,
    var text: String,
    val imageUrl: String?,
    var likeCount: Int,
    var commentCount: Int
)
