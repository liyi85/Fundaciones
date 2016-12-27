package com.fundaciones.andrearodriguez.fundaciones.perrolist.ui.adapter;

import android.widget.ImageView;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 9/25/16.
 */
public interface OnItemClickListener {
    void onPerroClick(Paticas paticas);
    void onShareclick(Paticas paticas, ImageView img);
    void onDeleteClick(Paticas paticas);
}
