package ominext.android.vn.nicepic.data.Module;

import android.app.Application;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MyPC on 24/07/2017.
 */
@Module
public class FireBaseModule {
    @Provides
    public DatabaseReference getReference() {
        return FirebaseDatabase.getInstance().getReference();
    }

    private Application application;

    public FireBaseModule(Application application) {
        this.application = application;
    }

    @Provides
    public FirebaseAuth getFirebaseAuth() {
        return FirebaseAuth.getInstance();
    }

    @Provides
    public FirebaseDatabase getFirebaseDatabase() {
        return FirebaseDatabase.getInstance();
    }
}
