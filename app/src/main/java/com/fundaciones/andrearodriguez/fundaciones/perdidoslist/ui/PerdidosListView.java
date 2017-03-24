package com.fundaciones.andrearodriguez.fundaciones.perdidoslist.ui;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public interface PerdidosListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addPerdido(Paticas pAticas);
    void removePerdido(Paticas paticas);
    void onPerdidoError(String error);
}
