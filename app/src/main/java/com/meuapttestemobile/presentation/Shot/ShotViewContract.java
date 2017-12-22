package com.meuapttestemobile.presentation.Shot;

import com.meuapttestemobile.data.model.Shot;
import com.meuapttestemobile.presentation.BaseView;

import java.util.List;

public interface ShotViewContract extends BaseView {
    void setupShotList(List<Shot> gists);

    void showError();
}
