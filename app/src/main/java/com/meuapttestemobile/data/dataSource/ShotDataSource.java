package com.meuapttestemobile.data.dataSource;

import com.meuapttestemobile.data.model.Shot;

import java.util.List;

import rx.Observable;

public interface ShotDataSource {
    Observable<List<Shot>> getShots(String accessToken, int page);
}
