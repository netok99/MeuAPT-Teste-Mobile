package com.meuapttestemobile.presentation.Shot

import android.util.Log
import com.meuapttestemobile.data.ShotDataSource
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers

class ShotPresenter(private val view: ShotContract.View,
                    private val dataSource: ShotDataSource) : ShotContract.Presenter {

    override fun getShots(accessToken: String, page: Int) {
        view.showLoadingIndicator(true)

        dataSource.getShots(accessToken, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate {
                    view.showLoadingIndicator(false)
                }
                .subscribe({ gists ->
                    view.setupShotList(gists)
                }, { error ->
                    Log.e("error", error.toString())
                    view.showError()
                })
    }

}
