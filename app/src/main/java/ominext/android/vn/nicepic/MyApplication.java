package ominext.android.vn.nicepic;

import android.app.Application;

import javax.inject.Inject;

import ominext.android.vn.nicepic.di.component.ApplicationComponent;
import ominext.android.vn.nicepic.di.component.DaggerApplicationComponent;
import ominext.android.vn.nicepic.di.module.ApplicationModule;

/**
 * Created by MyPC on 24/07/2017.
 */

public class MyApplication extends Application {
    @Inject
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        buildComponent();
        applicationComponent.injectTo(this);
    }

    private void buildComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
