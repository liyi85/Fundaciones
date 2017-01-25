package com.fundaciones.andrearodriguez.fundaciones.otroslist.ui;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;

/**
 * Created by andrearodriguez on 1/24/17.
 */

public interface OtrosListView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void addOtros(Paticas paticas);
    void removeOtros(Paticas paticas);
    void onOtrosError(String error);
}
