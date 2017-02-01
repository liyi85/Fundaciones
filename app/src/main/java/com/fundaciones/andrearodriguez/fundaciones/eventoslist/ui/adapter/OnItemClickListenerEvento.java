package com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui.adapter;

import android.widget.ImageView;

import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public interface OnItemClickListenerEvento {
    void onEventoClick(Eventos eventos);
    void onShareclick(Eventos eventos, ImageView img);
    void onDeleteClick(Eventos eventos);
}
