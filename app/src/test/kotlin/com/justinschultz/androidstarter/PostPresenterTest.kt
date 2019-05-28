package com.justinschultz.androidstarter

import com.justinschultz.androidstarter.ui.post.PostPresenter
import com.justinschultz.androidstarter.ui.post.PostView
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.junit.MockitoJUnitRunner


/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(MockitoJUnitRunner::class)
class PostPresenterTest {
    @Mock
    lateinit var view: PostView

    private var presenter: PostPresenter? = null

    @Before
    @Throws(Exception::class)
    fun setUp() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { _ -> Schedulers.trampoline() }
        presenter = PostPresenter(view)
    }

    @Test
    fun onViewDestroyed_ShowLoading() {
        presenter?.onViewCreated()
        verify<PostView>(view, times(1)).showLoading()
    }

    @Test
    fun onViewDestroyed_HideLoading() {
        presenter?.onViewDestroyed()
        verify<PostView>(view, times(1)).hideLoading()
    }
}
