package com.meuapttestemobile.data

import com.meuapttestemobile.data.model.Shot
import rx.Observable

interface ShotDataSource {
    fun getShots(accessToken: String, page: Int): Observable<List<Shot>>
}
