package com.fundaciones.andrearodriguez.fundaciones.addevento;

import com.fundaciones.andrearodriguez.fundaciones.addevento.event.AddEventoEvent;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseEventosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorage;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.ImageStorageFinishedListener;

import java.io.File;

/**
 * Created by andrearodriguez on 1/26/17.
 */

public class AddEventoRespositoryImp implements AddEventoRespository{

    private EvenBus eventBus;
    private FirebaseEventosAPI firebaseEventosAPI;
    private ImageStorage imageStorage;

    public AddEventoRespositoryImp(EvenBus eventBus, FirebaseEventosAPI firebaseEventosAPI, ImageStorage imageStorage) {
        this.eventBus = eventBus;
        this.firebaseEventosAPI = firebaseEventosAPI;
        this.imageStorage = imageStorage;
    }

    @Override
    public void uploadPhoto(final String nombreevento, final String fecha, final String lugar, final String hora, String path, final String tipo) {
        final String newPhotoId = firebaseEventosAPI.create();
        final Eventos eventos = new Eventos();
        eventos.setId(newPhotoId);
        eventos.setEmail(firebaseEventosAPI.getAuthEmail());

        post(AddEventoEvent.UPLOAD_INIT);

        ImageStorageFinishedListener listener = new ImageStorageFinishedListener() {

            @Override
            public void onSuccess() {
                String url = imageStorage.getImageUrl(newPhotoId);
                eventos.setUrl(url);

                eventos.setNombre(nombreevento);
                eventos.setLugar(lugar);
                eventos.setFecha(fecha);
                eventos.setHora(hora);
                eventos.setTipoevento(tipo);
                firebaseEventosAPI.update(eventos);
                post(AddEventoEvent.UPLOAD_COMPLETE);
            }

            @Override
            public void onError(String error) {
                post(AddEventoEvent.UPLOAD_ERROR, error);
            }
        };

        imageStorage.upload(new File(path), newPhotoId, listener);
    }

    private void post(int type) {
        post(type, null);
    }
    private  void post(int type, String error){
        AddEventoEvent event = new AddEventoEvent();
        event.setType(type);
        event.setError(error);
        eventBus.post(event);
    }
}
