package ominext.android.vn.nicepic.di.component;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Component;
import ominext.android.vn.nicepic.MyApplication;
import ominext.android.vn.nicepic.di.ApplicationContext;
import ominext.android.vn.nicepic.di.module.ApplicationModule;

/**
 * Created by MyPC on 24/07/2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void injectTo(MyApplication myApplication);

    @ApplicationContext
    Context context();

    Application getApplication();

}
