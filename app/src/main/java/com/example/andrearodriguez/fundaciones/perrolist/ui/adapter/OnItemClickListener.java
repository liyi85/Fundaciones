package com.example.andrearodriguez.fundaciones.perrolist.ui.adapter;

import android.widget.ImageView;

import com.example.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 9/25/16.
 */
public interface OnItemClickListener {
    void onPerroClick(Paticas paticas);
    void onShareclick(Paticas paticas, ImageView img);
    void onDeleteClick(Paticas paticas);
}
