package com.example.andrearodriguez.fundaciones.addgato;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public interface AddGatoRepository {
    void uploadPhoto(String name, String edad, String sexo, String tamano, String path, String esteril, String vacuna, String discapacidad);
}
