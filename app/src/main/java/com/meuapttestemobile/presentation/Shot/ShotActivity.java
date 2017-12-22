package com.meuapttestemobile.presentation.Shot;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.meuapttestemobile.R;
import com.meuapttestemobile.data.model.Shot;
import com.meuapttestemobile.presentation.BaseActivity;
import com.meuapttestemobile.presentation.PresenterModule;
import com.meuapttestemobile.presentation.UiComponent;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class ShotActivity extends BaseActivity implements ShotViewContract {

    @Inject
    ShotPresenterContract presenter;
    private SwipeRefreshLayout swipeRefresh;
    private List<Shot> shots;
    private final String keySavedInstanceState = "shots";

    static {
        System.loadLibrary("keys");
    }
    public native String getNativeKey();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots);
        UiComponent uiComponent = getAppComponent().plus(new PresenterModule(this));
        uiComponent.inject(this);

        setupUI();

        if (savedInstanceState == null) {
            getShots();
        } else {
            setupShotList(savedInstanceState.getParcelableArrayList(keySavedInstanceState));
        }
    }

    private void setupUI() {
        setupToolbar();
        setupSwipeRefresh();
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_shot_activity));
        setSupportActionBar(toolbar);
    }

    private void setupSwipeRefresh() {
        swipeRefresh = findViewById(R.id.swipeRefresh);
        swipeRefresh.setOnRefreshListener(this::getShots);
    }

    private void getShots() {
        presenter.getShots(getNativeKey(), 1);
    }

    @Override
    public void setupShotList(@NotNull List<Shot> shots) {
        this.shots = shots;
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        RecyclerView shotsList = findViewById(R.id.shotsList);
        shotsList.setLayoutManager(layoutManager);
        shotsList.addItemDecoration(new DividerItemDecoration(shotsList.getContext(), layoutManager.getOrientation()));
        shotsList.setAdapter(new ShotAdapter(this, shots));
        shotsList.setHasFixedSize(true);
    }

    @Override
    public void showError() {
        Toast.makeText(this, getString(R.string.error_message_shot), Toast.LENGTH_LONG).show();
    }

    @Override
    public void showLoadingIndicator(boolean isLoading) {
        swipeRefresh.setRefreshing(isLoading);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        if (shots != null && !shots.isEmpty()) {
            savedInstanceState.putParcelableArrayList(keySavedInstanceState,
                    (ArrayList<? extends Parcelable>) shots);
        }
    }
}
