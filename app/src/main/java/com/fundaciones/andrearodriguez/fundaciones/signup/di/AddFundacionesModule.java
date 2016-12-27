package com.fundaciones.andrearodriguez.fundaciones.signup.di;

import com.fundaciones.andrearodriguez.fundaciones.domine.FirebaseFundacionesAPI;
import com.fundaciones.andrearodriguez.fundaciones.libs.base.EvenBus;
import com.fundaciones.andrearodriguez.fundaciones.signup.AddFundacionInteractor;
import com.fundaciones.andrearodriguez.fundaciones.signup.AddFundacionInteractorImp;
import com.fundaciones.andrearodriguez.fundaciones.signup.AddFundacionPresenter;
import com.fundaciones.andrearodriguez.fundaciones.signup.AddFundacionPresenterImp;
import com.fundaciones.andrearodriguez.fundaciones.signup.AddFundacionRepository;
import com.fundaciones.andrearodriguez.fundaciones.signup.AddFundacionRepositoryImp;
import com.fundaciones.andrearodriguez.fundaciones.signup.ui.AddFundacionView;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 9/22/16.
 */
@Module
public class AddFundacionesModule {

    private AddFundacionView view;

    public AddFundacionesModule(AddFundacionView view) {
        this.view = view;
    }
    @Provides
    @Singleton
    AddFundacionView providesAddFundacionView(){
        return this.view;
    }

    @Provides
    @Singleton
    AddFundacionPresenter providesAddFundacionPresenter(AddFundacionView view, EvenBus evenBus, AddFundacionInteractor fundacionInteractor){
        return new AddFundacionPresenterImp(view, evenBus, fundacionInteractor);
    }
    @Provides
    @Singleton
    AddFundacionInteractor providesAddFundacionInteractor(AddFundacionRepository repository){
        return new AddFundacionInteractorImp(repository);
    }
    @Provides
    @Singleton
    AddFundacionRepository providesAddFundacionRepository (EvenBus evenBus, FirebaseFundacionesAPI fundacionesAPI){
        return new AddFundacionRepositoryImp(evenBus, fundacionesAPI);
    }
}
