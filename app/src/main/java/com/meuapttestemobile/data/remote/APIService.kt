package com.meuapttestemobile.data.remote

import com.meuapttestemobile.data.model.Shot
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface APIService {

    @GET("shots?access_token=84ec8334d80acb8d849f9d2f24038d25df170bcce1b3d0d998543cfb81bb7e8e")
    fun getShots(@Query("access_token") accessToken: String,
                 @Query("page") page: Int = 1): Observable<List<Shot>>
}