package com.fundaciones.andrearodriguez.fundaciones.perdidoslist.adapter;

import android.widget.ImageView;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public interface OnItemClickPerdidos {
    void onPerdidoslick(Paticas paticas);
    void onShareclick(Paticas paticas, ImageView img);
    void onDeleteClick(Paticas paticas);
}
