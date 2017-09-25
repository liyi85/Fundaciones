package com.fundaciones.andrearodriguez.fundaciones.addperdidos;

/**
 * Created by andrearodriguez on 9/25/17.
 */

public class AddPerdidosInteractorImp implements AddPerdidosInteractor{

    AddPerdidosRepository repository;

    public AddPerdidosInteractorImp(AddPerdidosRepository repository) {
        this.repository = repository;
    }

    @Override
    public void excecute(String name, String especie, String edad, String sexo, String path, String esteril, String vacuna, String discapacidad) {
        repository.uploadPhoto(name, especie, edad, sexo, path, esteril, vacuna, discapacidad);
    }
}
