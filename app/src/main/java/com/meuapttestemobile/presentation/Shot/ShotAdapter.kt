package com.meuapttestemobile.presentation.Shot

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.meuapttestemobile.R
import com.meuapttestemobile.data.model.Shot
import com.meuapttestemobile.presentation.ShotDetail.ShotDetailActivity
import com.meuapttestemobile.presentation.loadUrl
import kotlinx.android.synthetic.main.item_shot.view.*
import java.text.SimpleDateFormat

class ShotAdapter(private val context: Context, private val shots: List<Shot>) :
        RecyclerView.Adapter<ShotViewHolder>() {

    override fun getItemCount(): Int = shots.size

    override fun onBindViewHolder(holder: ShotViewHolder, position: Int) = holder.bind(shots[position])

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): ShotViewHolder =
            ShotViewHolder(LayoutInflater.from(context).inflate(R.layout.item_shot, parent, false))

}

class ShotViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    @SuppressLint("SimpleDateFormat")
    fun bind(shot: Shot) = with(itemView) {

        txtTitle.text = shot.title
        txtCreatedAt.text = SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(shot.created_at)
        txtViewsCount.text = shot.views_count.toString()
        imgShot.loadUrl(shot.images.normal)


        setOnClickListener {
            val intent = Intent(context, ShotDetailActivity::class.java)
//            intent.putExtra("id", gist.id)
            context.startActivity(intent)
//            activity.overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left)
        }
    }
}
