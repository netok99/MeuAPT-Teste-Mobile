package com.meuapttestemobile.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.support.v7.app.AppCompatActivity
import com.meuapttestemobile.App
import com.meuapttestemobile.AppComponent

abstract class BaseActivity : AppCompatActivity() {
    fun getAppComponent(): AppComponent = (application as App).appComponent

    fun isOnline(context: Context): Boolean {
        val connectionManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val netInfo = connectionManager.activeNetworkInfo
        return netInfo != null && netInfo.isConnectedOrConnecting
    }
}

interface BaseView {
    fun showLoadingIndicator(isLoading: Boolean)
}
