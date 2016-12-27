package com.fundaciones.andrearodriguez.fundaciones.domine;

import com.fundaciones.andrearodriguez.fundaciones.entities.Paticas;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by andrearodriguez on 12/21/16.
 */

public class FirebaseGatosAPI {
    private Firebase firebase;
    private ChildEventListener gatosEventListener;

    public FirebaseGatosAPI(Firebase firebase) {
        this.firebase = firebase.child("Gatos");
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
        if (gatosEventListener == null){
            gatosEventListener = new ChildEventListener() {
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
            firebase.addChildEventListener(gatosEventListener);
        }
    }
    public void unsubscribe(){
        if (gatosEventListener != null) {
            firebase.removeEventListener(gatosEventListener);
        }
    }
    public String create(){
        return firebase.push().getKey();
    }



    public void update(Paticas paticas){
        this.firebase.child(paticas.getId()).setValue(paticas);

    }

    public void remove (Paticas paticas, FirebaseActionListenerCallback listenerCallback){
        this.firebase.child(paticas.getId()).removeValue();
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

