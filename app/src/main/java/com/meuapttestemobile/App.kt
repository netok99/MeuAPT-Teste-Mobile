package com.meuapttestemobile

import android.app.Application

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        buildComponent()
    }

    private fun buildComponent() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(AppModule(this))
                .build()
    }
}
