package com.fundaciones.andrearodriguez.fundaciones.addotros;

import com.fundaciones.andrearodriguez.fundaciones.addotros.events.AddOtrosEvent;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseOtrosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorage;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorageFinishedListener;

import java.io.File;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public class AddOtrosRepositoryImp implements AddOtrosRepository{

    private EvenBus eventBus;
    private FirebaseOtrosAPI firebaseOtrosAPI;
    private ImageStorage imageStorage;

    public AddOtrosRepositoryImp(EvenBus eventBus, FirebaseOtrosAPI firebaseOtrosAPI, ImageStorage imageStorage) {
        this.eventBus = eventBus;
        this.firebaseOtrosAPI = firebaseOtrosAPI;
        this.imageStorage = imageStorage;
    }

    @Override
    public void uploadPhoto(final String name, final String especie, final String edad, final String sexo, String path, final String esteril, final String vacuna, final String discapacidad) {
        final String newPhotoId = firebaseOtrosAPI.create();
        final Paticas paticas = new Paticas();
        paticas.setId(newPhotoId);
        paticas.setEmail(firebaseOtrosAPI.getAuthEmail());

        post(AddOtrosEvent.UPLOAD_INIT);

        ImageStorageFinishedListener listener = new ImageStorageFinishedListener() {

            @Override
            public void onSuccess() {
                String url = imageStorage.getImageUrl(newPhotoId);
                paticas.setUrl(url);

                paticas.setNombre(name);
                paticas.setEdad(edad);
                paticas.setSexo(sexo);
                paticas.setEsterilizacion(esteril);
                paticas.setVacunacion(vacuna);
                paticas.setDiscapacitado(discapacidad);
                paticas.setEspecie(especie);
                firebaseOtrosAPI.update(paticas);
                post(AddOtrosEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(AddOtrosEvent.UPLOAD_ERROR, error);
            }
        };

        imageStorage.upload(new File(path), newPhotoId, listener);
    }

    private void post(int type) {
        post(type, null);
    }
    private  void post(int type, String error){
        AddOtrosEvent event = new AddOtrosEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}
