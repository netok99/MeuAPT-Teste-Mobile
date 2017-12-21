package com.meuapttestemobile.presentation

import com.meuapttestemobile.data.ShotDataSource
import com.meuapttestemobile.presentation.Shot.ShotActivity
import com.meuapttestemobile.presentation.Shot.ShotContract
import com.meuapttestemobile.presentation.Shot.ShotPresenter
import dagger.Module
import dagger.Provides
import dagger.Subcomponent

@Subcomponent(modules = [(PresenterModule::class)])
interface UiComponent {
    fun inject(activity: ShotActivity)
}

@Module
open class PresenterModule(private val view: BaseView) {
    @Provides
    fun provideGistsPresenter(dataSource: ShotDataSource): ShotContract.Presenter {
        if (view !is ShotContract.View) {
            throw ClassCastException("view should be ShotContract.View")
        }
        return ShotPresenter(view, dataSource)
    }
//
//    @Provides
//    fun provideGistPresenter(dataSource: GistDetailDataSource): GistDetailContract.Presenter {
//        if (view !is GistDetailContract.View) {
//            throw ClassCastException("view should be GistDetailContract.View")
//        }
//        return GistDetailPresenter(view, dataSource)
//    }
}

