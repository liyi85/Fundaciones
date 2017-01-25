package com.fundaciones.andrearodriguez.fundaciones.otroslist;

import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseActionListenerCallback;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseEventListenerCallback;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseOtrosAPI;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.otroslist.events.OtrosListEvent;

/**
 * Created by andrearodriguez on 1/24/17.
 */

public class OtrosListRepositoryImp implements OtrosListRepository{

    private EvenBus eventBus;
    private FirebaseOtrosAPI firebaseOtrosAPI;

    public OtrosListRepositoryImp(EvenBus eventBus, FirebaseOtrosAPI firebaseOtrosAPI) {
        this.eventBus = eventBus;
        this.firebaseOtrosAPI = firebaseOtrosAPI;
    }

    @Override
    public void subscribe() {
        firebaseOtrosAPI.checkForData(new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(OtrosListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(OtrosListEvent.READ_EVENT, "");
                }
            }
        });
        firebaseOtrosAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Paticas paticas = dataSnapshot.getValue(Paticas.class);
                String email = firebaseOtrosAPI.getAuthEmail();
                paticas.setId(dataSnapshot.getKey());

                boolean publishedByMy = (paticas.getEmail()).equals(email);
                paticas.setPublishedByMe(publishedByMy);
                post(OtrosListEvent.READ_EVENT, paticas);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Paticas paticas = dataSnapshot.getValue(Paticas.class);
                paticas.setId(dataSnapshot.getKey());

                post(OtrosListEvent.DELETE_EVENT, paticas);
            }

            @Override
            public void onCancell(FirebaseError error) {
                post(OtrosListEvent.READ_EVENT, error.getMessage());
            }

        });
    }

    @Override
    public void unsubscribe() {
        firebaseOtrosAPI.unsubscribe();

    }

    @Override
    public void removeOtros(final Paticas paticas) {
        firebaseOtrosAPI.remove(paticas, new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {
                post(OtrosListEvent.DELETE_EVENT, paticas);
            }

            @Override
            public void onError(FirebaseError error) {
                post(OtrosListEvent.DELETE_EVENT, error.getMessage());
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
        OtrosListEvent event = new OtrosListEvent();
        event.setType(type);
        event.setError(error);
        event.setPaticas(paticas);
        eventBus.post(event);
    }
}
