package com.example.andrearodriguez.fundaciones.gatolist;

import com.example.andrearodriguez.fundaciones.domine.FirebaseActionListenerCallback;
import com.example.andrearodriguez.fundaciones.domine.FirebaseEventListenerCallback;
import com.example.andrearodriguez.fundaciones.domine.FirebaseGatosAPI;
import com.example.andrearodriguez.fundaciones.entities.Paticas;
import com.example.andrearodriguez.fundaciones.gatolist.events.GatoListEvent;
import com.example.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 12/21/16.
 */

public class GatoListRepositoryImp implements GatoListRepository {

    private EvenBus eventBus;
    private FirebaseGatosAPI firebaseGatosAPI;

    public GatoListRepositoryImp(EvenBus eventBus, FirebaseGatosAPI firebaseGatosAPI) {
        this.eventBus = eventBus;
        this.firebaseGatosAPI = firebaseGatosAPI;
    }
    @Override
    public void subscribe() {
        firebaseGatosAPI.checkForData(new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(GatoListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(GatoListEvent.READ_EVENT, "");
                }
            }
        });
        firebaseGatosAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Paticas paticas = dataSnapshot.getValue(Paticas.class);
                String email = firebaseGatosAPI.getAuthEmail();
                paticas.setId(dataSnapshot.getKey());

                boolean publishedByMy = (paticas.getEmail()).equals(email);
                paticas.setPublishedByMe(publishedByMy);
                post(GatoListEvent.READ_EVENT, paticas);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Paticas paticas = dataSnapshot.getValue(Paticas.class);
                paticas.setId(dataSnapshot.getKey());

                post(GatoListEvent.DELETE_EVENT, paticas);
            }

            @Override
            public void onCancell(FirebaseError error) {
                post(GatoListEvent.READ_EVENT, error.getMessage());
            }

        });
    }

    @Override
    public void unsubscribe() {
        firebaseGatosAPI.unsubscribe();

    }

    @Override
    public void removeGato(final Paticas paticas) {
        firebaseGatosAPI.remove(paticas, new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {
                post(GatoListEvent.DELETE_EVENT, paticas);
            }

            @Override
            public void onError(FirebaseError error) {
                post(GatoListEvent.DELETE_EVENT, error.getMessage());
            }
        });

    }

    private void post(int type, Paticas paticas) {
        post(type, paticas, null);
    }

    private void post(int type, String error) {
        post(type, null, error);
    }

    private void post(int type, Paticas paticas, String error) {
        GatoListEvent event = new GatoListEvent();
        event.setType(type);
        event.setError(error);
        event.setPaticas(paticas);
        eventBus.post(event);
    }
}
