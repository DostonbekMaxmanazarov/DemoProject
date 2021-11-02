package com.example.demoprojectsealedcoroutinehilt.di.module

import com.example.demoprojectsealedcoroutinehilt.data.repository.PostRepository
import com.example.demoprojectsealedcoroutinehilt.mvp.contract.IPostContract
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent

@Module
@InstallIn(ActivityComponent::class)
interface RepositoryModule {
    @Binds
    fun getRepository(postRepository: PostRepository): IPostContract.Model
}