package com.example.demoprojectsealedcoroutinehilt.mvp.presenter

import com.example.demoprojectsealedcoroutinehilt.mvp.contract.IPostContract
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import moxy.InjectViewState
import moxy.MvpPresenter
import moxy.presenterScope
import javax.inject.Inject

@InjectViewState
class PostPresenter @Inject constructor(private var repository: IPostContract.Model) :
    MvpPresenter<IPostContract.View>(), IPostContract.Presenter {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()
        loadPost()
    }

    override fun loadPost() {
        viewState.showRefresh()
        presenterScope.launch {
            try {
                repository.loadPost().onData {
                    viewState.addPosts(it)
                }.onResource {
                    viewState.showMessage(it)
                }.onMessage {
                    viewState.showMessage(it)
                }.onFailure {
                }
            } catch (e: Exception) {
                e.printStackTrace()
                viewState.onFail()
            } finally {
                viewState.hideRefresh()
            }
        }
    }

    override fun cancel() {

    }
}