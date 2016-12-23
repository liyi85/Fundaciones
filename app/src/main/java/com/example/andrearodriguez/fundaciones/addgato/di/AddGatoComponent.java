package com.example.andrearodriguez.fundaciones.addgato.di;

import com.example.andrearodriguez.fundaciones.FundacionAppModule;
import com.example.andrearodriguez.fundaciones.addgato.ui.AddGatoFragment;
import com.example.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.example.andrearodriguez.fundaciones.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 12/22/16.
 */
@Singleton
@Component(modules = {AddGatoModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface AddGatoComponent {
    void inject(AddGatoFragment fragment);
}
