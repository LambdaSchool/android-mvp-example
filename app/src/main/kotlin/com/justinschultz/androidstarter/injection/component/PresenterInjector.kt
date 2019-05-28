package com.justinschultz.androidstarter.injection.component

import com.justinschultz.androidstarter.base.BaseView
import com.justinschultz.androidstarter.injection.module.ContextModule
import com.justinschultz.androidstarter.injection.module.NetworkModule
import com.justinschultz.androidstarter.ui.post.PostPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton


/**
 * Component providing inject() methods for presenters.
 */
@Singleton
@Component(modules = [(ContextModule::class), (NetworkModule::class)])
interface PresenterInjector {
    /**
     * Injects required dependencies into the specified PostPresenter.
     * @param postPresenter PostPresenter in which to inject the dependencies
     */
    fun inject(postPresenter: PostPresenter)

    @Component.Builder
    interface Builder {
        fun build(): PresenterInjector

        fun networkModule(networkModule: NetworkModule): Builder
        fun contextModule(contextModule: ContextModule): Builder

        @BindsInstance
        fun baseView(baseView: BaseView): Builder
    }
}