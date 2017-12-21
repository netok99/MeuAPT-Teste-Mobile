package com.meuapttestemobile.data

import com.meuapttestemobile.App
import com.meuapttestemobile.data.remote.APIService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideShotRepository(api: APIService, app: App): ShotDataSource = ShotRepository(api, app)
}
