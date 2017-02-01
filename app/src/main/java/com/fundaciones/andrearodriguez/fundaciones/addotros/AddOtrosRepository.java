package com.fundaciones.andrearodriguez.fundaciones.addotros;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public interface AddOtrosRepository {
    void uploadPhoto(String name, String especie, String edad, String sexo, String path, String esteril, String vacuna, String discapacidad);
}
