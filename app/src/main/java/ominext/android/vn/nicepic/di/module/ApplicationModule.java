package ominext.android.vn.nicepic.di.module;

import android.app.Application;
import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import ominext.android.vn.nicepic.data.firebase.BaseBaseFireBaseIpml;
import ominext.android.vn.nicepic.data.firebase.BaseFireBase;
import ominext.android.vn.nicepic.di.ApplicationContext;
import ominext.android.vn.nicepic.di.ReferentPic;
import ominext.android.vn.nicepic.utils.Constants;

/**
 * Created by MyPC on 24/07/2017.
 */
@Module
public class ApplicationModule {
    private Application mApplication;
    public ApplicationModule(Application application) {
        this.mApplication = application;
    }
    @Provides
    @ApplicationContext
    Context provideContext(){
        return mApplication;
    }

    @Provides
    Application provideApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    FirebaseDatabase getFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }

    @Provides
    @Singleton
    FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    @Singleton
    FirebaseStorage getFirebaseStorage() {
        return FirebaseStorage.getInstance();
    }

    @Provides
    @Singleton
    BaseFireBase provideBaseFireBase(BaseBaseFireBaseIpml baseFireBase){
        return baseFireBase;
    }

    @Provides
    @ReferentPic
    String provideReferencePic() {
        return Constants.PICS_PATH;
    }




}
