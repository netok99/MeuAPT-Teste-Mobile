package com.meuapttestemobile.presentation.ShotDetail;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.meuapttestemobile.R;
import com.meuapttestemobile.data.model.Shot;
import com.meuapttestemobile.presentation.BaseActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;

public class ShotDetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shots_detail);

        setupUi(getIntent().getParcelableExtra("shot"));
    }

    private void setupUi(@NotNull final Shot shot) {
        setupToolbar();
        setupViews(shot);
    }

    private void setupToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_shot_detail_activity));
        setSupportActionBar(toolbar);
    }

    @SuppressLint("SimpleDateFormat")
    private void setupViews(@NotNull final Shot shot) {
        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(shot.getTitle());

        if (shot.getDescription() != null) {
            TextView txtDescription = findViewById(R.id.txtDescription);
            txtDescription.setText(Html.fromHtml(shot.getDescription()));
        }

        TextView txtViewsCount = findViewById(R.id.txtViewsCount);
        txtViewsCount.setText(getString(R.string.views_count, String.valueOf(shot.getViewsCount())));

        TextView txtCommentsCount = findViewById(R.id.txtCommentsCount);
        txtCommentsCount.setText(getString(R.string.comments_count, String.valueOf(shot.getCommentsCount())));

        TextView txtCreatedAt = findViewById(R.id.txtCreatedAt);
        txtCreatedAt.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(shot.getCreatedAt()));

        ImageView imgShot = findViewById(R.id.imgShot);
        Picasso.with(this).load(shot.getImages().getHidpi()).into(imgShot);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                supportFinishAfterTransition();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
