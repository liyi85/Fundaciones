package com.fundaciones.andrearodriguez.fundaciones.main.di;

import com.fundaciones.andrearodriguez.fundaciones.FundacionAppModule;
import com.fundaciones.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.fundaciones.andrearodriguez.fundaciones.main.ui.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 9/25/16.
 */

@Singleton
@Component(modules = {MainModule.class, DomainModule.class, FundacionAppModule.class})
public interface MainComponent {
    void inject(MainActivity mainActivity);
}
