package com.meuapttestemobile

import android.content.Context
import com.meuapttestemobile.data.APIModule
import com.meuapttestemobile.data.DataModule
import com.meuapttestemobile.presentation.PresenterModule
import com.meuapttestemobile.presentation.UiComponent
import dagger.Component
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Singleton
@Component(modules = [(AppModule::class), (DataModule::class), (APIModule::class)])
interface AppComponent {
    fun inject(app: App)
    operator fun plus(presenterModule: PresenterModule): UiComponent
}

@Module
class AppModule(val app: App) {

    @Provides
    @Singleton
    fun provideContext(): Context = app

    @Provides
    @Singleton
    fun provideApp(): App = app
}
