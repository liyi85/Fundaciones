package com.fundaciones.andrearodriguez.fundaciones.addotros.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.addotros.ui.AddOTrosFragment;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 1/25/17.
 */

@Singleton
@Component(modules = {AddOtrosModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface AddOtroComponent {
    void inject(AddOTrosFragment fragment);
}
