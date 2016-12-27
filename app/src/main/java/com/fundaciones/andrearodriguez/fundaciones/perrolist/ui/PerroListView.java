package com.fundaciones.andrearodriguez.fundaciones.perrolist.ui;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 9/25/16.
 */
public interface PerroListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addPerro(Paticas paticas);
    void removePerro(Paticas paticas);
    void onPerroError(String error);
}
