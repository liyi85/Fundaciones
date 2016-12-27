package com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.adapter;

import android.widget.ImageView;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 12/20/16.
 */

public interface OnItemClickListener {

    void onGatoClick(Paticas paticas);
    void onShareclick(Paticas paticas, ImageView img);
    void onDeleteClick(Paticas paticas);
}
