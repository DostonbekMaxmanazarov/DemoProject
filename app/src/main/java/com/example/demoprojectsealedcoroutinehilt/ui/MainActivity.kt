package com.example.demoprojectsealedcoroutinehilt.ui

import android.os.Bundle
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.demoprojectsealedcoroutinehilt.data.model.Post
import com.example.demoprojectsealedcoroutinehilt.databinding.ActivityMainBinding
import com.example.demoprojectsealedcoroutinehilt.mvp.contract.IPostContract
import com.example.demoprojectsealedcoroutinehilt.mvp.presenter.PostPresenter
import com.example.demoprojectsealedcoroutinehilt.ui.adapter.PostAdapter
import com.example.demoprojectsealedcoroutinehilt.utils.MessageData
import dagger.hilt.android.AndroidEntryPoint
import moxy.MvpAppCompatActivity
import moxy.presenter.InjectPresenter
import moxy.presenter.ProvidePresenter
import javax.inject.Inject
import javax.inject.Provider

@AndroidEntryPoint
class MainActivity : MvpAppCompatActivity(), IPostContract.View {

    private lateinit var binding: ActivityMainBinding

    private lateinit var adapter: PostAdapter

    @InjectPresenter
    lateinit var postPresenter: PostPresenter

    @Inject
    lateinit var presenterProvider: Provider<PostPresenter>

    @ProvidePresenter
    fun providePresenter(): PostPresenter {
        return presenterProvider.get()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        adapter = PostAdapter(this)

        initRv()
    }

    override fun showRefresh() {
        binding.swipeRefresh.setOnRefreshListener {
            postPresenter.loadPost()
        }
    }

    override fun hideRefresh() {
        binding.swipeRefresh.isRefreshing = false
    }

    override fun addPosts(posts: List<Post>) {
        adapter.submitPost(posts)
    }

    override fun onFail() {
        showDialog("Fail", "Something went wrong please try again")
    }

    override fun showMessage(message: MessageData) {

    }

    override fun showMessage(message: Int) {
        showDialog("ResMessage", message)
    }

    override fun showMessage(message: String) {
        showDialog("Message", message)
    }

    private fun initRv() {
        binding.rv.layoutManager = LinearLayoutManager(this)
        binding.rv.adapter = adapter
    }

    private fun showDialog(title: String, message: String) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .create()
        dialog.show()
    }

    private fun showDialog(title: String, @StringRes message: Int) {
        val dialog = AlertDialog.Builder(this)
            .setTitle(title)
            .setMessage(message)
            .create()
        dialog.show()
    }
}