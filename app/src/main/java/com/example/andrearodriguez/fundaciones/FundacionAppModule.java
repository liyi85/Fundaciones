package com.example.andrearodriguez.fundaciones;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 9/21/16.
 */
@Module
public class FundacionAppModule {
    FundacionesApp app;

    public FundacionAppModule(FundacionesApp app) {
        this.app = app;
    }

    @Provides
    @Singleton
    Context providesApplicationContext(){
        return app.getApplicationContext();
    }

    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application){
        return application.getSharedPreferences(app.getSharedPreferencesName(), Context.MODE_PRIVATE);
    }
    @Provides
    @Singleton
    Application providesAplication(){
        return this.app;
    }
}
