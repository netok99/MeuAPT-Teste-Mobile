package com.meuapttestemobile.presentation;

import com.meuapttestemobile.presentation.Shot.ShotActivity;

import dagger.Subcomponent;

@Subcomponent(modules = {PresenterModule.class})
public interface UiComponent {
    void inject(ShotActivity activity);
}
