package com.fundaciones.andrearodriguez.fundaciones.addperdidos;

import com.fundaciones.andrearodriguez.fundaciones.addperdidos.events.AddPerdidosEvent;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebasePerdidosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorage;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorageFinishedListener;

import java.io.File;

/**
 * Created by andrearodriguez on 9/25/17.
 */

public class AddPerdidosRepositoryImp implements AddPerdidosRepository {

    private EvenBus eventBus;
    private FirebasePerdidosAPI firebaseOtrosAPI;
    private ImageStorage imageStorage;

    public AddPerdidosRepositoryImp(EvenBus eventBus, FirebasePerdidosAPI firebaseOtrosAPI, ImageStorage imageStorage) {
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

        post(AddPerdidosEvent.UPLOAD_INIT);

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
                post(AddPerdidosEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(AddPerdidosEvent.UPLOAD_ERROR, error);
            }
        };

        imageStorage.upload(new File(path), newPhotoId, listener);
    }

    private void post(int type) {
        post(type, null);
    }

    private void post(int type, String error) {
        AddPerdidosEvent event = new AddPerdidosEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}