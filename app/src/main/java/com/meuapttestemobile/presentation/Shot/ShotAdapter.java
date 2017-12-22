package com.meuapttestemobile.presentation.Shot;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.meuapttestemobile.R;
import com.meuapttestemobile.data.model.Shot;
import com.meuapttestemobile.presentation.ShotDetail.ShotDetailActivity;
import com.squareup.picasso.Picasso;

import org.jetbrains.annotations.NotNull;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

class ShotAdapter extends RecyclerView.Adapter<ShotViewHolder> {

    private final ShotActivity activity;
    private List<Shot> shots = new ArrayList<>();

    ShotAdapter(@NotNull ShotActivity activity) {
        this.activity = activity;
    }

    void addItem(List<Shot> shots) {
        this.shots.addAll(shots);
        notifyDataSetChanged();
    }

    void clearItems() {
        int size = shots.size();
        shots.clear();
        notifyItemRangeRemoved(0, size);
    }

    @Override
    public ShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShotViewHolder(LayoutInflater.from(activity).inflate(R.layout.item_shot, parent, false));
    }

    @SuppressLint("SimpleDateFormat")
    @Override
    public void onBindViewHolder(ShotViewHolder holder, int position) {
        holder.bind(activity, shots.get(position));
    }

    @Override
    public int getItemCount() {
        return shots.size();
    }
}

class ShotViewHolder extends RecyclerView.ViewHolder {

    private final View itemView;

    ShotViewHolder(@NotNull View itemView) {
        super(itemView);
        this.itemView = itemView;
    }

    @SuppressLint("SimpleDateFormat")
    void bind(@NotNull ShotActivity activity, @NotNull Shot shot) {
        TextView txtTitle = itemView.findViewById(R.id.txtTitle);
        txtTitle.setText(shot.getTitle());

        TextView txtCreatedAt = itemView.findViewById(R.id.txtCreatedAt);
        txtCreatedAt.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(shot.getCreatedAt()));

        TextView txtViewsCount = itemView.findViewById(R.id.txtViewsCount);
        txtViewsCount.setText(String.valueOf(shot.getViewsCount()));

        ImageView imgShot = itemView.findViewById(R.id.imgShot);
        Picasso.with(activity).load(shot.getImages().getNormal()).into(imgShot);

        itemView.getRootView().setOnClickListener(view -> {
            Intent intent = new Intent(activity, ShotDetailActivity.class);
            intent.putExtra("shot", shot);
            ActivityOptionsCompat options = ActivityOptionsCompat.
                    makeSceneTransitionAnimation(activity, imgShot, "shot");
            activity.startActivity(intent);
        });
    }
}
