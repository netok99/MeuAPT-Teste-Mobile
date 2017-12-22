package com.meuapttestemobile.presentation.Shot;

import com.meuapttestemobile.data.dataSource.ShotDataSource;
import com.meuapttestemobile.data.model.Shot;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
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
                .doAfterTerminate(() -> view.showLoadingIndicator(false))
                .subscribe(new Observer<List<Shot>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showError();
                    }

                    @Override
                    public void onNext(List<Shot> shots) {
                        view.setupShotList(shots);
                    }
                });
    }
}
