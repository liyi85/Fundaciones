package com.fundaciones.andrearodriguez.fundaciones.eventoslist.ui;

import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public interface EventosListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addEvento(Eventos eventos);
    void removeEvento(Eventos eventos);
    void onEventoError(String error);
}
