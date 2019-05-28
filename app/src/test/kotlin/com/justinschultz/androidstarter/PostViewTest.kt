package com.justinschultz.androidstarter

import android.view.View
import android.widget.ProgressBar
import com.justinschultz.androidstarter.model.Post
import com.justinschultz.androidstarter.ui.post.PostActivity
import org.hamcrest.MatcherAssert.assertThat
import org.hamcrest.Matchers.equalTo
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner

@RunWith(RobolectricTestRunner::class)
class PostViewTest {
    @Mock
    lateinit var listOfItems:  ArrayList<Post>

    private var postActivity: PostActivity? = null
    private var progressBar: ProgressBar? = null


    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)

        postActivity = Robolectric.setupActivity(PostActivity::class.java)
        progressBar = postActivity?.findViewById(R.id.progressBar)
        postActivity?.updatePosts(listOfItems)
    }

    @Test
    fun `test show loading`() {
        postActivity?.showLoading()
        assertThat(progressBar?.visibility, equalTo(View.VISIBLE))
    }

}