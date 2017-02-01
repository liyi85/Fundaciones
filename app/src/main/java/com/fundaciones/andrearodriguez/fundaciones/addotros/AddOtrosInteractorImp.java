package com.fundaciones.andrearodriguez.fundaciones.addotros;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public class AddOtrosInteractorImp implements AddOtrosInteractor{

    AddOtrosRepository repository;

    public AddOtrosInteractorImp(AddOtrosRepository repository) {
        this.repository = repository;
    }

    @Override
    public void excecute(String name, String especie, String edad, String sexo, String path, String esteril, String vacuna, String discapacidad) {
        repository.uploadPhoto(name, especie, edad, sexo, path, esteril, vacuna, discapacidad);
    }
}
