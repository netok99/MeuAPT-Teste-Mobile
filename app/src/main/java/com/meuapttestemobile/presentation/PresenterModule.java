package com.meuapttestemobile.presentation;

import com.meuapttestemobile.data.dataSource.ShotDataSource;
import com.meuapttestemobile.presentation.Shot.ShotPresenter;
import com.meuapttestemobile.presentation.Shot.ShotPresenterContract;
import com.meuapttestemobile.presentation.Shot.ShotViewContract;

import dagger.Module;
import dagger.Provides;

@Module
public class PresenterModule {

    private final BaseView view;

    public PresenterModule(BaseView view) {
        this.view = view;
    }

    @Provides
    ShotPresenterContract provideGistsPresenter(ShotDataSource dataSource) {
        if (!(view instanceof ShotViewContract)) {
            throw new ClassCastException("view should be ShotContract.View");
        }
        return new ShotPresenter((ShotViewContract) view, dataSource);
    }
}
