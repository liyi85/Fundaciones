package com.example.andrearodriguez.fundaciones.addperro;

/**
 * Created by andrearodriguez on 8/17/16.
 */
public interface AddPerroRepository {
    void uploadPhoto(String name, String edad, String sexo, String tamano, String path, String esteril, String vacuna);
}
