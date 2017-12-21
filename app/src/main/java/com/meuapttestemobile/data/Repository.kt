package com.meuapttestemobile.data

import com.meuapttestemobile.App
import com.meuapttestemobile.data.model.Shot
import com.meuapttestemobile.data.remote.APIService
import rx.Observable

class ShotRepository(private val api: APIService, private val app: App) : ShotDataSource {
    override fun getShots(accessToken: String, page: Int): Observable<List<Shot>> = api.getShots(accessToken, page)
}
