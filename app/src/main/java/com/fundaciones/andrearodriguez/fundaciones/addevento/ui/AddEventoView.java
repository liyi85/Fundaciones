package com.fundaciones.andrearodriguez.fundaciones.addevento.ui;

/**
 * Created by andrearodriguez on 1/26/17.
 */

public interface AddEventoView {
    void onUploadInit();
    void onUploadComplete();
    void onUploadError(String error);
}
