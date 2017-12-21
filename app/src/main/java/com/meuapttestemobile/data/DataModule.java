package com.meuapttestemobile.data;

import com.meuapttestemobile.App;
import com.meuapttestemobile.data.dataSource.ShotDataSource;
import com.meuapttestemobile.data.repository.ShotRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class DataModule {

    @Provides
    @Singleton
    ShotDataSource provideShotRepository(APIService api, App app) {
        return new ShotRepository(api, app);
    }
}
