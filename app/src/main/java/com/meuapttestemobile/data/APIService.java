package com.meuapttestemobile.data;

import com.meuapttestemobile.data.model.Shot;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface APIService {

    @GET("shots")
    Observable<List<Shot>> getShots(@Query("access_token") String accessToken, @Query("page") int page);
}
