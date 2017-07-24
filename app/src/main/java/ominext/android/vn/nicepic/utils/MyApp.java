package ominext.android.vn.nicepic.utils;

import android.app.Application;

import ominext.android.vn.nicepic.data.Component.DaggerFireBaseComponent;
import ominext.android.vn.nicepic.data.Component.FireBaseComponent;
import ominext.android.vn.nicepic.data.Module.FireBaseModule;

/**
 * Created by MyPC on 24/07/2017.
 */

public class MyApp extends Application {
    private FireBaseComponent fireBaseComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        fireBaseComponent=DaggerFireBaseComponent.builder()
                .fireBaseModule(new FireBaseModule(this))
                .build();
    }
    public FireBaseComponent getFireBaseComponent() {
        return fireBaseComponent;
    }
}
