package ominext.android.vn.nicepic.di.module;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import dagger.Module;
import dagger.Provides;

/**
 * Created by MyPC on 25/07/2017.
 */
@Module
public class FirebaseModule {
    @Provides
    public FirebaseAuth getFirebaseAuth(){
        return FirebaseAuth.getInstance();
    }
    @Provides
    public FirebaseDatabase getFirebaseDatabase(){
        return FirebaseDatabase.getInstance();
    }
    @Provides
    public FirebaseStorage getFirebaseStorage(){
        return FirebaseStorage.getInstance();
    }
    @Provides
    public FirebaseUser getFirebaseUser(){
        return FirebaseAuth.getInstance().getCurrentUser();
    }
    @Provides
    public DatabaseReference getReference(){return FirebaseDatabase.getInstance().getReference();
    }
    @Provides
    public StorageReference getStorageReference(){
        return FirebaseStorage.getInstance().getReference();
    }

}
