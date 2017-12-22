package com.meuapttestemobile.presentation.ShotDetail;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.meuapttestemobile.R;
import com.meuapttestemobile.data.model.Shot;
import com.meuapttestemobile.presentation.BaseActivity;
import com.squareup.picasso.Callback;
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
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }

    @SuppressLint("SimpleDateFormat")
    private void setupViews(@NotNull final Shot shot) {
        TextView txtTitle = findViewById(R.id.txtTitle);
        txtTitle.setText(shot.getTitle());

        TextView txtDescription = findViewById(R.id.txtDescription);
        if (shot.getDescription() != null) {
            txtDescription.setText(getString(R.string.description, Html.fromHtml(shot.getDescription())));
        } else {
            txtDescription.setText(getString(R.string.empty_description));
        }

        TextView txtViewsCount = findViewById(R.id.txtViewsCount);
        txtViewsCount.setText(getString(R.string.views_count, String.valueOf(shot.getViewsCount())));

        TextView txtCommentsCount = findViewById(R.id.txtCommentsCount);
        txtCommentsCount.setText(getString(R.string.comments_count, String.valueOf(shot.getCommentsCount())));

        TextView txtCreatedAt = findViewById(R.id.txtCreatedAt);
        txtCreatedAt.setText(getString(R.string.created_at,
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(shot.getCreatedAt())));

        ImageView imgShot = findViewById(R.id.imgShot);
        Picasso.with(this).load(shot.getImages().getHidpi()).into(imgShot, new Callback() {
            @Override
            public void onSuccess() {
                scheduleStartPostponedTransition(imgShot);
            }

            @Override
            public void onError() {
                Toast.makeText(ShotDetailActivity.this,
                        getString(R.string.error_load_image), Toast.LENGTH_LONG).show();
            }
        });
    }

    private void scheduleStartPostponedTransition(final View sharedElement) {
        sharedElement.getViewTreeObserver().addOnPreDrawListener(
                new ViewTreeObserver.OnPreDrawListener() {
                    @Override
                    public boolean onPreDraw() {
                        sharedElement.getViewTreeObserver().removeOnPreDrawListener(this);
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                            startPostponedEnterTransition();
                        }
                        return true;
                    }
                });
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        supportFinishAfterTransition();
    }
}
