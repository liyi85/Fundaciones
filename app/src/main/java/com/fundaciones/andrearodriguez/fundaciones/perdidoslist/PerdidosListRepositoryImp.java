package com.fundaciones.andrearodriguez.fundaciones.perdidoslist;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseActionListenerCallback;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseEventListenerCallback;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebasePerdidosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.perdidoslist.event.PerdidosListEvent;

/**
 * Created by andrearodriguez on 3/24/17.
 */

public class PerdidosListRepositoryImp implements PerdidosListRepository{

    private EvenBus eventBus;
    private FirebasePerdidosAPI firebasePerdidosAPI;

    public PerdidosListRepositoryImp(EvenBus eventBus, FirebasePerdidosAPI firebasePerdidosAPI) {
        this.eventBus = eventBus;
        this.firebasePerdidosAPI = firebasePerdidosAPI;
    }

    @Override
    public void subscribe() {
        firebasePerdidosAPI.checkForData(new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(PerdidosListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(PerdidosListEvent.READ_EVENT, "");
                }
            }
        });
        firebasePerdidosAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Paticas paticas = dataSnapshot.getValue(Paticas.class);
                String email = firebasePerdidosAPI.getAuthEmail();
                paticas.setId(dataSnapshot.getKey());

                boolean publishedByMy = (paticas.getEmail()).equals(email);
                paticas.setPublishedByMe(publishedByMy);
                post(PerdidosListEvent.READ_EVENT, paticas);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Paticas paticas = dataSnapshot.getValue(Paticas.class);
                paticas.setId(dataSnapshot.getKey());

                post(PerdidosListEvent.DELETE_EVENT, paticas);
            }

            @Override
            public void onCancell(FirebaseError error) {
                post(PerdidosListEvent.READ_EVENT, error.getMessage());
            }

        });
    }

    @Override
    public void unsubscribe() {
        firebasePerdidosAPI.unsubscribe();

    }

    @Override
    public void removePerdido(final Paticas paticas) {

        firebasePerdidosAPI.remove(paticas, new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {
                post(PerdidosListEvent.DELETE_EVENT, paticas);
            }

            @Override
            public void onError(FirebaseError error) {
                post(PerdidosListEvent.DELETE_EVENT, error.getMessage());
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
        PerdidosListEvent event = new PerdidosListEvent();
        event.setType(type);
        event.setError(error);
        event.setPaticas(paticas);
        eventBus.post(event);
    }
}
