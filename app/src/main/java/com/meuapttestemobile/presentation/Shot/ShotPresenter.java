package com.meuapttestemobile.presentation.Shot;

import android.util.Log;

import com.meuapttestemobile.data.dataSource.ShotDataSource;
import com.meuapttestemobile.data.model.Shot;

import java.util.List;

import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class ShotPresenter implements ShotPresenterContract {

    private ShotViewContract view;
    private ShotDataSource dataSource;

    public ShotPresenter(ShotViewContract view, ShotDataSource dataSource) {
        this.view = view;
        this.dataSource = dataSource;
    }


    @Override
    public void getShots(String accessToken, int page) {
        view.showLoadingIndicator(true);

        dataSource.getShots(accessToken, page)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        view.showLoadingIndicator(false);
                    }
                })
                .subscribe(new Action1<List<Shot>>() {
                    @Override
                    public void call(List<Shot> shots) {
                        view.setupShotList(shots);
                    }

                });
    }
}
