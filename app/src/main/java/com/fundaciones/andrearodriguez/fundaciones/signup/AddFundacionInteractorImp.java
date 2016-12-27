package com.fundaciones.andrearodriguez.fundaciones.signup;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public class AddFundacionInteractorImp implements AddFundacionInteractor{

    AddFundacionRepository repository;

    public AddFundacionInteractorImp(AddFundacionRepository repository) {
        this.repository = repository;
    }

    @Override
    public void excecute(String name, String direccion, String telefono, String persona, String correo) {
        repository.uploadFundacion(name, direccion, telefono, persona, correo);
    }
}
