package com.fundaciones.andrearodriguez.fundaciones.eventoslist;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseActionListenerCallback;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseEventListenerCallback;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseEventosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;
import com.fundaciones.andrearodriguez.fundaciones.eventoslist.events.EventosListEvent;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;

/**
 * Created by andrearodriguez on 1/25/17.
 */

public class EventosListRespositoryImp implements EventosListRespository{

    private EvenBus eventBus;
    private FirebaseEventosAPI firebaseEventosAPI;

    public EventosListRespositoryImp(EvenBus eventBus, FirebaseEventosAPI firebaseEventosAPI) {
        this.eventBus = eventBus;
        this.firebaseEventosAPI = firebaseEventosAPI;
    }

    @Override
    public void subscribe() {
        firebaseEventosAPI.checkForData(new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(EventosListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(EventosListEvent.READ_EVENT, "");
                }
            }
        });
        firebaseEventosAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Eventos evento = dataSnapshot.getValue(Eventos.class);
                String email = firebaseEventosAPI.getAuthEmail();
                evento.setId(dataSnapshot.getKey());

                boolean publishedByMy = (evento.getEmail()).equals(email);
                evento.setPublishedByMe(publishedByMy);
                post(EventosListEvent.READ_EVENT, evento);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Eventos evento = dataSnapshot.getValue(Eventos.class);
                evento.setId(dataSnapshot.getKey());

                post(EventosListEvent.DELETE_EVENT, evento);
            }

            @Override
            public void onCancell(FirebaseError error) {
                post(EventosListEvent.READ_EVENT, error.getMessage());
            }

        });
    }

    @Override
    public void unsubscribe() {
        firebaseEventosAPI.unsubscribe();

    }

    @Override
    public void removeEvento(final Eventos eventos) {
        firebaseEventosAPI.remove(eventos, new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {
                post(EventosListEvent.DELETE_EVENT, eventos);
            }

            @Override
            public void onError(FirebaseError error) {
                post(EventosListEvent.DELETE_EVENT, error.getMessage());
            }
        });

    }

    private void post(int type, Eventos eventos) {
        post(type, eventos, null);
    }

    private void post(int type, String error) {
        post(type, null, error);
    }

    private void post(int type, Eventos eventos, String error) {
        EventosListEvent event = new EventosListEvent();
        event.setType(type);
        event.setError(error);
        event.setEventos(eventos);
        eventBus.post(event);
    }

}
