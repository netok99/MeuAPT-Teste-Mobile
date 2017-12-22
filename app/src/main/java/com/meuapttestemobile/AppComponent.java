package com.meuapttestemobile;

import com.meuapttestemobile.data.APIModule;
import com.meuapttestemobile.data.DataModule;
import com.meuapttestemobile.presentation.PresenterModule;
import com.meuapttestemobile.presentation.UiComponent;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class, APIModule.class})
public interface AppComponent {
    void inject(App app);
    UiComponent plus(PresenterModule presenterModule);
}
