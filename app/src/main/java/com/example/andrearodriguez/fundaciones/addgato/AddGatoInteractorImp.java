package com.example.andrearodriguez.fundaciones.addgato;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public class AddGatoInteractorImp implements AddGatoInteractor{

    AddGatoRepository repository;

    public AddGatoInteractorImp(AddGatoRepository repository) {
        this.repository = repository;
    }

    @Override
    public void excecute(String name, String edad, String sexo, String tamano, String path, String esteril, String vacuna) {
        repository.uploadPhoto(name, edad, sexo, tamano, path, esteril, vacuna);
    }
}
