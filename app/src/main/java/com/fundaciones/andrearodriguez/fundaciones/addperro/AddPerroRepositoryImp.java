package com.fundaciones.andrearodriguez.fundaciones.addperro;

import com.fundaciones.andrearodriguez.fundaciones.addperro.event.AddPerroEvent;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebasePerrosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorage;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorageFinishedListener;

import java.io.File;

/**
 * Created by andrearodriguez on 8/17/16.
 */
public class AddPerroRepositoryImp implements AddPerroRepository{
    private EvenBus eventBus;
    private FirebasePerrosAPI firebasePerrosAPI;
    private ImageStorage imageStorage;

    public AddPerroRepositoryImp(EvenBus eventBus, FirebasePerrosAPI firebasePerrosAPI, ImageStorage imageStorage) {
        this.eventBus = eventBus;
        this.firebasePerrosAPI = firebasePerrosAPI;
        this.imageStorage = imageStorage;
    }

    @Override
    public void uploadPhoto(final String nombre, final String edad, final String sexo, final String tamano, String path, final String esteril, final String vacuna, final String discapacidad) {
        final String newPhotoId = firebasePerrosAPI.create();
        final Paticas paticas = new Paticas();
        paticas.setId(newPhotoId);
        paticas.setEmail(firebasePerrosAPI.getAuthEmail());

        post(AddPerroEvent.UPLOAD_INIT);

        ImageStorageFinishedListener listener = new ImageStorageFinishedListener() {

            @Override
            public void onSuccess() {
                String url = imageStorage.getImageUrl(newPhotoId);
                paticas.setUrl(url);

                paticas.setNombre(nombre);
                paticas.setEdad(edad);
                paticas.setSexo(sexo);
                paticas.setTamaño(tamano);
                paticas.setEsterilizacion(esteril);
                paticas.setVacunacion(vacuna);
                paticas.setDiscapacitado(discapacidad);
                firebasePerrosAPI.update(paticas);
                post(AddPerroEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(AddPerroEvent.UPLOAD_ERROR, error);
            }
        };

        imageStorage.upload(new File(path), newPhotoId, listener);
    }

    private void post(int type) {
        post(type, null);
    }
    private  void post(int type, String error){
        AddPerroEvent event = new AddPerroEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}
