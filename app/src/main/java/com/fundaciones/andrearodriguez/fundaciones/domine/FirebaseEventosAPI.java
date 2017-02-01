package com.fundaciones.andrearodriguez.fundaciones.domine;

import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.fundaciones.andrearodriguez.fundaciones.entities.Eventos;

import java.util.Map;

/**
 * Created by andrearodriguez on 1/26/17.
 */

public class FirebaseEventosAPI {
    private Firebase firebase;
    private ChildEventListener eventoEventListener;

    public FirebaseEventosAPI(Firebase firebase) {
        this.firebase = firebase.child("Eventos");
    }
    public void checkForData(final FirebaseActionListenerCallback listenerCallback){
        firebase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.getChildrenCount() > 0){
                    listenerCallback.onSucces();
                }else {
                    listenerCallback.onError(null);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                listenerCallback.onError(firebaseError);
            }
        });
    }
    public void subscribe(final FirebaseEventListenerCallback listenerCallback){
        if (eventoEventListener == null){
            eventoEventListener = new ChildEventListener() {
                @Override
                public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                    listenerCallback.onChildAdded(dataSnapshot);
                }

                @Override
                public void onChildChanged(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onChildRemoved(DataSnapshot dataSnapshot) {
                    listenerCallback.onChildRemoved(dataSnapshot);
                }

                @Override
                public void onChildMoved(DataSnapshot dataSnapshot, String s) {

                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {
                    listenerCallback.onCancell(firebaseError);
                }
            };
            firebase.addChildEventListener(eventoEventListener);
        }
    }
    public void unsubscribe(){
        if (eventoEventListener != null) {
            firebase.removeEventListener(eventoEventListener);
        }
    }
    public String create(){
        return firebase.push().getKey();
    }



    public void update(Eventos eventos){
        this.firebase.child(eventos.getId()).setValue(eventos);

    }

    public void remove (Eventos eventos, FirebaseActionListenerCallback listenerCallback){
        this.firebase.child(eventos.getId()).removeValue();
        listenerCallback.onSucces();
    }

    public String getAuthEmail(){
        String email = null;

        if (firebase.getAuth() != null){
            Map<String, Object> providerData = firebase.getAuth().getProviderData();
            email = providerData.get("email").toString();
        }
        return email;

    }
}
