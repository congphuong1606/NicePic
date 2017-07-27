package ominext.android.vn.nicepic;

import android.app.Application;

import ominext.android.vn.nicepic.di.component.ApplicationComponent;
import ominext.android.vn.nicepic.di.component.DaggerApplicationComponent;
import ominext.android.vn.nicepic.di.module.ApplicationModule;


/**
 * Created by MyPC on 24/07/2017.
 */

public class MyApplication extends Application {
    private static MyApplication instance;
    ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        buildAppComponent();
    }

    private void buildAppComponent() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();

    }

    public ApplicationComponent getComponent() {
        return applicationComponent;
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }

    public static synchronized MyApplication get() {
        return instance;
    }
}
