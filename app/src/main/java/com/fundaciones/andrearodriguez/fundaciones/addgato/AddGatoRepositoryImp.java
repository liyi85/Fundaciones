package com.fundaciones.andrearodriguez.fundaciones.addgato;

import com.fundaciones.andrearodriguez.fundaciones.addgato.event.AddGatoEvent;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseGatosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorage;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorageFinishedListener;

import java.io.File;

/**
 * Created by andrearodriguez on 12/22/16.
 */

public class AddGatoRepositoryImp implements AddGatoRepository{

    private EvenBus evenBus;
    private FirebaseGatosAPI firebaseGatosAPI;
    private ImageStorage imageStorage;

    public AddGatoRepositoryImp(EvenBus evenBus, FirebaseGatosAPI firebaseGatosAPI, ImageStorage imageStorage) {
        this.evenBus = evenBus;
        this.firebaseGatosAPI = firebaseGatosAPI;
        this.imageStorage = imageStorage;
    }

    @Override
    public void uploadPhoto(final String name, final String edad, final String sexo, final String tamano, String path, final String esteril, final String vacuna, final String discapacidad) {
        final String newPhotoId = firebaseGatosAPI.create();
        final Paticas paticas = new Paticas();
        paticas.setId(newPhotoId);
        paticas.setEmail(firebaseGatosAPI.getAuthEmail());

        post(AddGatoEvent.UPLOAD_INIT);

        ImageStorageFinishedListener listener = new ImageStorageFinishedListener() {

            @Override
            public void onSuccess() {
                String url = imageStorage.getImageUrl(newPhotoId);
                paticas.setUrl(url);

                paticas.setNombre(name);
                paticas.setEdad(edad);
                paticas.setSexo(sexo);
                paticas.setTamaño(tamano);
                paticas.setEsterilizacion(esteril);
                paticas.setVacunacion(vacuna);
                paticas.setDiscapacitado(discapacidad);
                firebaseGatosAPI.update(paticas);
                post(AddGatoEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(AddGatoEvent.UPLOAD_ERROR, error);
            }
        };

        imageStorage.upload(new File(path), newPhotoId, listener);
    }

    private void post(int type) {
        post(type, null);
    }
    private  void post(int type, String error){
        AddGatoEvent event = new AddGatoEvent();
        event.setType(type);
        event.setError(error);
        evenBus.post(event);
    }
}
