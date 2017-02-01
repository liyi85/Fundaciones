package com.fundaciones.andrearodriguez.fundaciones.addevento;

/**
 * Created by andrearodriguez on 1/26/17.
 */

public interface AddEventoInteractor {

    void execute (String nombreevento, String fecha, String lugar, String hora, String path, String tipo);
}
