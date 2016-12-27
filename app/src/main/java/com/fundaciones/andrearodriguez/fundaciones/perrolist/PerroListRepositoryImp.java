package com.fundaciones.andrearodriguez.fundaciones.perrolist;

import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseActionListenerCallback;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebasePerrosAPI;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseEventListenerCallback;
import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.perrolist.events.PerroListEvent;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;

/**
 * Created by andrearodriguez on 8/18/16.
 */
public class PerroListRepositoryImp implements PerroListRepository{

    private EvenBus eventBus;
    private FirebasePerrosAPI firebasePerrosAPI;


    public PerroListRepositoryImp(EvenBus eventBus, FirebasePerrosAPI firebasePerrosAPI) {
        this.eventBus = eventBus;
        this.firebasePerrosAPI = firebasePerrosAPI;
    }

    @Override
    public void subscribe() {
        firebasePerrosAPI.checkForData(new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {

            }

            @Override
            public void onError(FirebaseError error) {
                if (error != null) {
                    post(PerroListEvent.READ_EVENT, error.getMessage());
                } else {
                    post(PerroListEvent.READ_EVENT, "");
                }
            }
        });
        firebasePerrosAPI.subscribe(new FirebaseEventListenerCallback() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot) {
                Paticas paticas = dataSnapshot.getValue(Paticas.class);
                String email = firebasePerrosAPI.getAuthEmail();
                paticas.setId(dataSnapshot.getKey());

                boolean publishedByMy = (paticas.getEmail()).equals(email);
                paticas.setPublishedByMe(publishedByMy);
                post(PerroListEvent.READ_EVENT, paticas);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {
                Paticas paticas = dataSnapshot.getValue(Paticas.class);
                paticas.setId(dataSnapshot.getKey());

                post(PerroListEvent.DELETE_EVENT, paticas);
            }

            @Override
            public void onCancell(FirebaseError error) {
                post(PerroListEvent.READ_EVENT, error.getMessage());
            }

        });
    }

    @Override
    public void unsubscribe() {
        firebasePerrosAPI.unsubscribe();

    }

    @Override
    public void removePerro(final Paticas paticas) {
        firebasePerrosAPI.remove(paticas, new FirebaseActionListenerCallback() {
            @Override
            public void onSucces() {
                post(PerroListEvent.DELETE_EVENT, paticas);
            }

            @Override
            public void onError(FirebaseError error) {
                post(PerroListEvent.DELETE_EVENT, error.getMessage());
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
        PerroListEvent event = new PerroListEvent();
        event.setType(type);
        event.setError(error);
        event.setPaticas(paticas);
        eventBus.post(event);
    }
}
