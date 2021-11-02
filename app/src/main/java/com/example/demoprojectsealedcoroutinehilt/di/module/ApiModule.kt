package com.example.demoprojectsealedcoroutinehilt.di.module

import com.example.demoprojectsealedcoroutinehilt.connection.network.PostApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class ApiModule {

    @Provides
    @Singleton
    fun getPostApi(retrofit: Retrofit): PostApi = retrofit.create(PostApi::class.java)

    /**
     * Context kerak bo'lganda Dagger Hilt maxsus contextidan foydalanamiz.
     * */
    /*@Provides
    @Singleton
    fun getPostApi(@ApplicationContext context:Context)=AppDatabase.inject(context)*/
}