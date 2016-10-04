package com.example.andrearodriguez.fundaciones.addperro.di;


import com.example.andrearodriguez.fundaciones.addperro.ui.AddPerroFragment;
import com.example.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.example.andrearodriguez.fundaciones.FundacionAppModule;
import com.example.andrearodriguez.fundaciones.addperro.ui.AddPerroFragment;
import com.example.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.example.andrearodriguez.fundaciones.libs.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 8/22/16.
 */

@Singleton
@Component (modules = {AddPerroModule.class, DomainModule.class, LibsModule.class, FundacionAppModule.class})
public interface AddPerroComponent {
    void inject(AddPerroFragment fragment);
}
