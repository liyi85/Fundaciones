package com.fundaciones.andrearodriguez.fundaciones.addperdidos;

/**
 * Created by andrearodriguez on 9/25/17.
 */

public interface AddPerdidosRepository {
    void uploadPhoto(String name, String especie, String edad, String sexo, String path, String esteril, String vacuna, String discapacidad);
}
