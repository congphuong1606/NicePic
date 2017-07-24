package ominext.android.vn.nicepic.data.Module;

import android.app.Application;

import dagger.Module;

/**
 * Created by MyPC on 24/07/2017.
 */
@Module
public class AppModule {
    private Application application;
    public AppModule(Application application) {
        this.application = application;
    }

}
