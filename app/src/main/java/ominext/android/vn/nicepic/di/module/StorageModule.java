package ominext.android.vn.nicepic.di.module;

import android.content.Context;
import android.content.SharedPreferences;

import dagger.Module;
import dagger.Provides;
import ominext.android.vn.nicepic.utils.Constants;

/**
 * Created by MyPC on 25/07/2017.
 */
@Module
public class StorageModule {

    @Provides
    public SharedPreferences getSharedPreferences(Context context){
        return context.getSharedPreferences(Constants.SPF_NAME, Context.MODE_PRIVATE);
    }
    @Provides
    public SharedPreferences.Editor getEditor(Context context){
        return context.getSharedPreferences(Constants.SPF_NAME, Context.MODE_PRIVATE).edit();

    }
}
