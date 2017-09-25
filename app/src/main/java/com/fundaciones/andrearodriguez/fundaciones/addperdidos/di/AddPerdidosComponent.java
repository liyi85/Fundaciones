package com.fundaciones.andrearodriguez.fundaciones.addperdidos.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.addperdidos.ui.AddPerdidosFragment;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 9/25/17.
 */




@Singleton
@Component(modules = {AddPerdidosModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface AddPerdidosComponent {
    void inject(AddPerdidosFragment fragment);
}

