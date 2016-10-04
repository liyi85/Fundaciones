package com.example.andrearodriguez.fundaciones.signup;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public interface AddFundacionRepository {
    void uploadFundacion(String name, String direccion, String telefono, String persona, String correo);
}
