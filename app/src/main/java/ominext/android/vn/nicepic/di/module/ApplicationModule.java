package ominext.android.vn.nicepic.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MyPC on 24/07/2017.
 */
@Module
public class ApplicationModule {
    private Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }
    @Provides
    public  Context getContext(){
        return context;
    }



}
