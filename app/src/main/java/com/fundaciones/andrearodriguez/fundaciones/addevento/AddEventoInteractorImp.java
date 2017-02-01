package com.fundaciones.andrearodriguez.fundaciones.addevento;

/**
 * Created by andrearodriguez on 1/26/17.
 */

public class AddEventoInteractorImp implements AddEventoInteractor{

    AddEventoRespository respository;

    public AddEventoInteractorImp(AddEventoRespository respository) {
        this.respository = respository;
    }

    @Override
    public void execute(String nombreevento, String fecha, String lugar, String hora, String path, String tipo) {
        respository.uploadPhoto(nombreevento, fecha, lugar, hora, path, tipo);
    }
}
