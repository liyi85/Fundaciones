package com.example.andrearodriguez.fundaciones.signup;

import com.example.andrearodriguez.fundaciones.domine.FirebaseFundacionesAPI;
import com.example.andrearodriguez.fundaciones.entities.Fundacion;
import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.example.andrearodriguez.fundaciones.signup.events.AddFundacionEvent;

/**
 * Created by andrearodriguez on 9/22/16.
 */
public class AddFundacionRepositoryImp implements AddFundacionRepository{

    private EvenBus evenBus;
    private FirebaseFundacionesAPI fundacionesAPI;

    public AddFundacionRepositoryImp(EvenBus evenBus, FirebaseFundacionesAPI fundacionesAPI) {
        this.evenBus = evenBus;
        this.fundacionesAPI = fundacionesAPI;
    }

    @Override
    public void uploadFundacion(String name, String direccion, String telefono, String persona, String correo) {
        final String newFundacionId = fundacionesAPI.create();
        final Fundacion fundacion= new Fundacion();
        fundacion.setId(newFundacionId);
        fundacion.setEmail(fundacionesAPI.getAuthEmail());

        post(AddFundacionEvent.UPLOAD_INIT);

        fundacion.setNombreFundacion(name);
        fundacion.setDireccion(direccion);
        fundacion.setTelefono(telefono);
        fundacion.setPersonaContacto(persona);
        fundacion.setEmail(correo);

        fundacionesAPI.update(fundacion);

        post(AddFundacionEvent.UPLOAD_COMPLETE);
    }


    private void post(int type) {
        post(type, null);
    }
    private  void post(int type, String error){
        AddFundacionEvent event = new AddFundacionEvent();
        event.setType(type);
        event.setError(error);
        evenBus.post(event);
    }
}
