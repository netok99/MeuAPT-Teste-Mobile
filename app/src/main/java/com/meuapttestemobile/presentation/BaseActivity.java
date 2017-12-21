package com.meuapttestemobile.presentation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;

import com.meuapttestemobile.App;
import com.meuapttestemobile.AppComponent;

public abstract class BaseActivity extends AppCompatActivity {
    public AppComponent getAppComponent() {
        return new App().getAppComponent();
    }

    public boolean isOnline(Context context) {
        ConnectivityManager connectionManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = connectionManager.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();
    }
}
