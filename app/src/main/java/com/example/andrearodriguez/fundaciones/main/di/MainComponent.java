package com.example.andrearodriguez.fundaciones.main.di;

import com.example.andrearodriguez.fundaciones.FundacionAppModule;
import com.example.andrearodriguez.fundaciones.domine.di.DomainModule;
import com.example.andrearodriguez.fundaciones.main.ui.MainActivity;

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
