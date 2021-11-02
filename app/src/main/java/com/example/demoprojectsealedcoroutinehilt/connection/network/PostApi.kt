package com.example.demoprojectsealedcoroutinehilt.connection.network

import com.example.demoprojectsealedcoroutinehilt.data.model.Post
import com.example.demoprojectsealedcoroutinehilt.utils.ResultData
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

const val BASE_URL = "https://jsonplaceholder.typicode.com/"

interface PostApi {
    /**
     * 1. Get all posts
     * */
    @GET("posts")
    suspend fun loadPost(): Response<List<Post>>
}