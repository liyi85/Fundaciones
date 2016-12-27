package com.fundaciones.andrearodriguez.fundaciones.gatolist.ui;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 12/20/16.
 */

public interface GatoLisView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addGato(Paticas paticas);
    void removeGato(Paticas paticas);
    void onGatoError(String error);
}
