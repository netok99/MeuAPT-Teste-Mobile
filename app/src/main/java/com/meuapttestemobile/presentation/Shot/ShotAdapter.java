package com.meuapttestemobile.presentation.Shot;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.meuapttestemobile.R;
import com.meuapttestemobile.data.model.Shot;

import java.text.SimpleDateFormat;
import java.util.List;

public class ShotAdapter extends RecyclerView.Adapter<ShotViewHolder> {

    private Context context;
    private List<Shot> shots;

    ShotAdapter(Context context, List<Shot> shots) {
        this.context = context;
        this.shots = shots;
    }

    @Override
    public ShotViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ShotViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shot, parent, false));
    }

    @Override
    public void onBindViewHolder(ShotViewHolder holder, int position) {
        holder.bind(shots.get(position));
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}

class ShotViewHolder extends RecyclerView.ViewHolder {

    private View view;

    ShotViewHolder(View itemView) {
        super(itemView);
        view = itemView;
    }

    @SuppressLint("SimpleDateFormat")
    void bind(Shot shot) {
        TextView txtTitle = view.findViewById(R.id.txtTitle);
        txtTitle.setText(shot.getTitle());

        TextView txtCreatedAt = view.findViewById(R.id.txtCreatedAt);
        txtCreatedAt.setText(new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(shot.getCreatedAt()));



//        txtTitle.text = shot.title
//        txtCreatedAt.text = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(shot.created_at)
//        txtViewsCount.text = shot.views_count.toString()
//        imgShot.loadUrl(shot.images.normal)


//        setOnClickListener {
//            val intent = Intent(context, ShotDetailActivity::class.java)
//            intent.putExtra("shot", shot)
//            context.startActivity(intent)
//            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
//        }
    }
}
