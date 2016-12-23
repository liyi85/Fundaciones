package com.example.andrearodriguez.fundaciones;

import android.app.Application;

import com.example.andrearodriguez.fundaciones.addgato.di.AddGatoComponent;
import com.example.andrearodriguez.fundaciones.addgato.di.AddGatoModule;
import com.example.andrearodriguez.fundaciones.addgato.di.DaggerAddGatoComponent;
import com.example.andrearodriguez.fundaciones.addgato.ui.AddGatoView;
import com.example.andrearodriguez.fundaciones.addperro.di.AddPerroComponent;
import com.example.andrearodriguez.fundaciones.addperro.di.AddPerroModule;
import com.example.andrearodriguez.fundaciones.addperro.di.DaggerAddPerroComponent;
import com.example.andrearodriguez.fundaciones.addperro.ui.AddPerroView;
import com.example.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.example.andrearodriguez.fundaciones.gatolist.di.DaggerGatoListComponent;
import com.example.andrearodriguez.fundaciones.gatolist.di.GatoListComponent;
import com.example.andrearodriguez.fundaciones.gatolist.di.GatoListModule;
import com.example.andrearodriguez.fundaciones.gatolist.ui.GatoLisView;
import com.example.andrearodriguez.fundaciones.gatolist.ui.GatoListFragment;
import com.example.andrearodriguez.fundaciones.gatolist.ui.adapter.OnItemClickListener;
import com.example.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.example.andrearodriguez.fundaciones.login.di.DaggerLoginComponent;
import com.example.andrearodriguez.fundaciones.login.di.LoginComponent;
import com.example.andrearodriguez.fundaciones.login.di.LoginModule;
import com.example.andrearodriguez.fundaciones.login.ui.LoginView;
import com.example.andrearodriguez.fundaciones.main.di.DaggerMainComponent;
import com.example.andrearodriguez.fundaciones.main.di.MainComponent;
import com.example.andrearodriguez.fundaciones.main.di.MainModule;
import com.example.andrearodriguez.fundaciones.perrolist.di.DaggerPerroLisComponent;
import com.example.andrearodriguez.fundaciones.perrolist.di.PerroLisComponent;
import com.example.andrearodriguez.fundaciones.perrolist.di.PerroListModule;
import com.example.andrearodriguez.fundaciones.perrolist.ui.PerroListFragment;
import com.example.andrearodriguez.fundaciones.perrolist.ui.PerroListView;
import com.example.andrearodriguez.fundaciones.signup.di.AddFundacionesModule;
import com.example.andrearodriguez.fundaciones.signup.di.DaggerSignupComponent;
import com.example.andrearodriguez.fundaciones.signup.di.SignupComponent;
import com.example.andrearodriguez.fundaciones.signup.di.SignupModule;
import com.example.andrearodriguez.fundaciones.signup.ui.AddFundacionView;
import com.example.andrearodriguez.fundaciones.signup.ui.SignupView;
import com.firebase.client.Firebase;

/**
 * Created by andrearodriguez on 9/21/16.
 */
public class FundacionesApp extends Application {

    private final static String EMAIL_KEY = "email";
    private final static String SHARED_PREFERENCES_NAME = "UsersPrefs";
    //private final static String FIREBASE_URL = "https://fundaciones.firebaseIO.com";
    private final static String FIREBASE_URL = "https://fundacionesapp.firebaseIO.com";

    private DomainModule domainModule;
    private FundacionAppModule fundacionAppModule;

    @Override
    public void onCreate() {
        super.onCreate();
        initFirebase();
        initModules();
    }

    private void initModules() {
        fundacionAppModule = new FundacionAppModule(this);
        domainModule = new DomainModule(FIREBASE_URL);
    }

    private void initFirebase() {
        Firebase.setAndroidContext(this);
    }

    public String getEmailKey() {
        return EMAIL_KEY;
    }

    public String getSharedPreferencesName() {
        return SHARED_PREFERENCES_NAME;
    }

    public LoginComponent getLoginComponent(LoginView view) {
        return DaggerLoginComponent
                .builder()
                .fundacionAppModule(fundacionAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .loginModule(new LoginModule(view))
                .build();
    }
    public SignupComponent getSignupComponent (SignupView view, AddFundacionView fundacionView){
        return DaggerSignupComponent
                .builder()
                .fundacionAppModule(fundacionAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .signupModule(new SignupModule(view))
                .addFundacionesModule(new AddFundacionesModule(fundacionView))
                .build();
    }

    public MainComponent getMainComponent(){
        return DaggerMainComponent
                .builder()
                .fundacionAppModule(fundacionAppModule)
                .domainModule(domainModule)
                .mainModule(new MainModule())
                .build();
    }
    public AddPerroComponent getAddPerroComponent (AddPerroView view){
        return DaggerAddPerroComponent
                .builder()
                .fundacionAppModule(fundacionAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .addPerroModule(new AddPerroModule(view))
                .build();
    }

    public PerroLisComponent getPerroLisComponent (PerroListFragment fragment, PerroListView view, com.example.andrearodriguez.fundaciones.perrolist.ui.adapter.OnItemClickListener clickListener){
        return DaggerPerroLisComponent
                .builder()
                .fundacionAppModule(fundacionAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .perroListModule(new PerroListModule(view, clickListener))
                .build();

    }

    public GatoListComponent getGatoListComponent (GatoListFragment fragment, GatoLisView view, OnItemClickListener clickListener){
        return DaggerGatoListComponent
                .builder()
                .fundacionAppModule(fundacionAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(fragment))
                .gatoListModule(new GatoListModule(view,clickListener))
                .build();
    }

    public AddGatoComponent getAddGatoComponent (AddGatoView view){
        return DaggerAddGatoComponent
                .builder()
                .fundacionAppModule(fundacionAppModule)
                .domainModule(domainModule)
                .libsModule(new LibsModule(null))
                .addGatoModule(new AddGatoModule(view))
                .build();
    }

}
