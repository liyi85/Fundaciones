package com.fundaciones.andrearodriguez.fundaciones;

import android.app.Application;

import com.fundaciones.andrearodriguez.fundaciones.addgato.di.AddGatoComponent;
import com.fundaciones.andrearodriguez.fundaciones.addgato.di.AddGatoModule;
import com.fundaciones.andrearodriguez.fundaciones.addgato.di.DaggerAddGatoComponent;
import com.fundaciones.andrearodriguez.fundaciones.addgato.ui.AddGatoView;
import com.fundaciones.andrearodriguez.fundaciones.addperro.di.AddPerroComponent;
import com.fundaciones.andrearodriguez.fundaciones.addperro.di.AddPerroModule;
import com.fundaciones.andrearodriguez.fundaciones.addperro.di.DaggerAddPerroComponent;
import com.fundaciones.andrearodriguez.fundaciones.addperro.ui.AddPerroView;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.di.DaggerGatoListComponent;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.di.GatoListComponent;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.di.GatoListModule;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.GatoLisView;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.GatoListFragment;
import com.fundaciones.andrearodriguez.fundaciones.gatolist.ui.adapter.OnItemClickListener;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;
import com.fundaciones.andrearodriguez.fundaciones.login.di.DaggerLoginComponent;
import com.fundaciones.andrearodriguez.fundaciones.login.di.LoginComponent;
import com.fundaciones.andrearodriguez.fundaciones.login.di.LoginModule;
import com.fundaciones.andrearodriguez.fundaciones.login.ui.LoginView;
import com.fundaciones.andrearodriguez.fundaciones.main.di.DaggerMainComponent;
import com.fundaciones.andrearodriguez.fundaciones.main.di.MainComponent;
import com.fundaciones.andrearodriguez.fundaciones.main.di.MainModule;
import com.fundaciones.andrearodriguez.fundaciones.perrolist.di.DaggerPerroLisComponent;
import com.fundaciones.andrearodriguez.fundaciones.perrolist.di.PerroLisComponent;
import com.fundaciones.andrearodriguez.fundaciones.perrolist.di.PerroListModule;
import com.fundaciones.andrearodriguez.fundaciones.perrolist.ui.PerroListFragment;
import com.fundaciones.andrearodriguez.fundaciones.perrolist.ui.PerroListView;
import com.fundaciones.andrearodriguez.fundaciones.signup.di.AddFundacionesModule;
import com.fundaciones.andrearodriguez.fundaciones.signup.di.DaggerSignupComponent;
import com.fundaciones.andrearodriguez.fundaciones.signup.di.SignupComponent;
import com.fundaciones.andrearodriguez.fundaciones.signup.di.SignupModule;
import com.fundaciones.andrearodriguez.fundaciones.signup.ui.AddFundacionView;
import com.fundaciones.andrearodriguez.fundaciones.signup.ui.SignupView;
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

    public PerroLisComponent getPerroLisComponent (PerroListFragment fragment, PerroListView view, com.fundaciones.andrearodriguez.fundaciones.perrolist.ui.adapter.OnItemClickListener clickListener){
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
