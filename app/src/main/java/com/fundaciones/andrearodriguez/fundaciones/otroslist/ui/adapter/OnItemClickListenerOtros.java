package com.fundaciones.andrearodriguez.fundaciones.otroslist.ui.adapter;

import android.widget.ImageView;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 1/24/17.
 */

public interface OnItemClickListenerOtros {
    void onOtrosClick(Paticas paticas);
    void onShareclick(Paticas paticas, ImageView img);
    void onDeleteClick(Paticas paticas);
}
