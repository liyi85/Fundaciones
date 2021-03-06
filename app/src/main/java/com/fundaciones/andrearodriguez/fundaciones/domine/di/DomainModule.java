package com.fundaciones.andrearodriguez.fundaciones.domine.di;

import com.firebase.client.Firebase;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseEventosAPI;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseFundacionesAPI;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseGatosAPI;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseLoginAPI;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseOtrosAPI;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebasePerdidosAPI;
import com.fundaciones.andrearodriguez.fundaciones.domine.FirebasePerrosAPI;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 9/21/16.
 */
@Module
public class DomainModule {

    String firebaseURL;

    public DomainModule(String firebaseURL) {
        this.firebaseURL = firebaseURL;
    }

    @Provides
    @Singleton
    FirebaseLoginAPI providesFirebaseLoginAPI(Firebase firebase){
        return new FirebaseLoginAPI(firebase);
    }
    @Provides
    @Singleton
    FirebaseFundacionesAPI providesFirebaseSignupAPI(Firebase firebase){
        return new FirebaseFundacionesAPI(firebase);
    }
    @Provides
    @Singleton
    FirebasePerrosAPI providesFirebasePerrosAPI(Firebase firebase){
        return new FirebasePerrosAPI(firebase);
    }
    @Provides
    @Singleton
    FirebaseGatosAPI providesFirebaseGatosAPI(Firebase firebase){
        return new FirebaseGatosAPI(firebase);
    }

    @Provides
    @Singleton
    FirebaseOtrosAPI providesFirebaseOtrosAPI(Firebase firebase){
        return new FirebaseOtrosAPI(firebase);
    }
    @Provides
    @Singleton
    FirebaseEventosAPI providesFirebaseEventosAPI(Firebase firebase){
        return new FirebaseEventosAPI(firebase);
    }

    @Provides
    @Singleton
    FirebasePerdidosAPI providesFirebasePerdidosAPI (Firebase firebase){
        return new FirebasePerdidosAPI(firebase);
    }

    @Provides
    @Singleton
    Firebase providesFirebase(String firebaseURL){
        return new Firebase(firebaseURL);
    }
    @Provides
    @Singleton
    String providesFirebaseURL(){
        return this.firebaseURL;
    }
}
