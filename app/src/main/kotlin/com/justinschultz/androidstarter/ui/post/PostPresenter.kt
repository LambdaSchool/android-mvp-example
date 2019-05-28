package com.justinschultz.androidstarter.ui.post

import com.justinschultz.androidstarter.R
import com.justinschultz.androidstarter.base.BasePresenter
import com.justinschultz.androidstarter.network.PostService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

/**
 * The Presenter that will present the Post view.
 * @param postView the Post view to be presented by the presenter
 * @property postService the API interface implementation
 * @property disposables the composite disposables
 */
class PostPresenter(postView: PostView) : BasePresenter<PostView>(postView) {
    @Inject
    lateinit var postService: PostService

    private var disposables = CompositeDisposable()

    override fun onViewCreated() {
        loadPosts()
    }

    private fun loadPosts() {
        view.showLoading()
        disposables.add(postService
                .getPosts()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .doOnTerminate { view.hideLoading() }
                .subscribe(
                        { postList -> view.updatePosts(postList) },
                        { view.showError(R.string.unknown_error) }
                ))
    }

    override fun onViewDestroyed() {
        view.hideLoading()
        disposables.clear()
    }
}