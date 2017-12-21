package com.meuapttestemobile.data.repository;

import com.meuapttestemobile.App;
import com.meuapttestemobile.data.dataSource.ShotDataSource;
import com.meuapttestemobile.data.model.Shot;
import com.meuapttestemobile.data.APIService;

import java.util.List;

import rx.Observable;

public class ShotRepository implements ShotDataSource {

    private APIService api;
    private App app;

    public ShotRepository(APIService api, App app) {
        this.api = api;
        this.app = app;
    }

    @Override
    public Observable<List<Shot>> getShots(String accessToken, int page) {
        return api.getShots(accessToken, page);
    }
}
