package com.fundaciones.andrearodriguez.fundaciones.domine;

import com.fundaciones.andrearodriguez.fundaciones.entities.Fundacion;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class FirebaseFundacionesAPI {
    private Firebase firebase;
    private ChildEventListener fundacionEventListener;

    public FirebaseFundacionesAPI(Firebase firebase) {
        this.firebase = firebase.child("Fundaciones");
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
        if (fundacionEventListener == null){
            fundacionEventListener = new ChildEventListener() {
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
            firebase.addChildEventListener(fundacionEventListener);
        }
    }
    public void unsubscribe(){
        if (fundacionEventListener != null) {
            firebase.removeEventListener(fundacionEventListener);
        }
    }
    public String create(){
        return firebase.push().getKey();
    }



    public void update(Fundacion fundacion){
        this.firebase.child(fundacion.getId()).setValue(fundacion);

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
