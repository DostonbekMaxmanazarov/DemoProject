package com.example.demoprojectsealedcoroutinehilt.mvp.contract

import androidx.annotation.StringRes
import com.example.demoprojectsealedcoroutinehilt.utils.ResultData
import com.example.demoprojectsealedcoroutinehilt.data.model.Post
import com.example.demoprojectsealedcoroutinehilt.utils.MessageData
import moxy.MvpView
import moxy.viewstate.strategy.alias.AddToEndSingle
import moxy.viewstate.strategy.alias.OneExecution

interface IPostContract {

    interface Model {
        /**
         *The loadPost () method in our model section performs the task of loading data from the server.
         * */
        suspend fun loadPost(): ResultData<List<Post>>

    }

    interface Presenter {

        /**
         * The logic that is executed when loading data from the server is performed in this method.
         * */
        fun loadPost()

        /**
         *
         * */
        fun cancel()

    }

    interface View : MvpView {

        /**
         * Refresh starts and loads the data
         * */
        @AddToEndSingle
        fun showRefresh()

        /**
         * Refresh closes
         * */
        @AddToEndSingle
        fun hideRefresh()

        /**
         * The uploaded data is output to the UI.
         * */
        @AddToEndSingle
        fun addPosts(posts: List<Post>)

        /**
         * issue errors to ui
         * */
        @OneExecution
        fun onFail()

        /**
         * issue messages to ui
         * */
        @AddToEndSingle
        fun showMessage(message: MessageData)

        @AddToEndSingle
        fun showMessage(@StringRes message: Int)

        @AddToEndSingle
        fun showMessage(message: String)

    }
}