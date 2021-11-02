package com.example.demoprojectsealedcoroutinehilt.data.repository

import com.example.demoprojectsealedcoroutinehilt.R
import com.example.demoprojectsealedcoroutinehilt.utils.ResultData
import com.example.demoprojectsealedcoroutinehilt.connection.network.PostApi
import com.example.demoprojectsealedcoroutinehilt.data.model.Post
import com.example.demoprojectsealedcoroutinehilt.mvp.contract.IPostContract
import javax.inject.Inject

class PostRepository @Inject constructor(private val api: PostApi) : IPostContract.Model {
    override suspend fun loadPost(): ResultData<List<Post>> {
        val response = api.loadPost()

        when (response.code()) {
            200 -> {
                return ResultData.data(response.body()!!)
            }
            404 -> {
                return ResultData.resource(R.string.not_found)
            }
            in 500..600 -> {
                return ResultData.resource(R.string.internal_server_error)
            }
            else -> return if (response.body() == null) {
                ResultData.resource(R.string.server_error)
            } else {
                ResultData.message(response.body().toString())
            }
        }
    }
}