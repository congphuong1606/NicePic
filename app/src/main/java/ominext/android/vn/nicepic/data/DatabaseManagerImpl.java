package ominext.android.vn.nicepic.data;

import android.content.Context;

import com.google.firebase.auth.FirebaseAuth;

import javax.inject.Inject;

import ominext.android.vn.nicepic.data.firebase.BaseFireBase;
import ominext.android.vn.nicepic.data.pref.PreferenceHelper;
import ominext.android.vn.nicepic.di.ApplicationContext;

/**
 * Created by MyPC on 25/07/2017.
 */

public class DatabaseManagerImpl implements DatabaseManager {
    private final PreferenceHelper prefHelper;
    private final Context context;
    private final BaseFireBase baseFireBase;
    private final FirebaseAuth mAuth;

    @Inject
    public DatabaseManagerImpl(PreferenceHelper prefHelper,
                               @ApplicationContext Context context,
                               BaseFireBase baseFireBase,
                               FirebaseAuth firebaseAuth) {
        this.prefHelper = prefHelper;
        this.context = context;
        this.baseFireBase = baseFireBase;
        this.mAuth = firebaseAuth;
    }

    @Override
    public String getCurrentUserId() {
        return null;
    }

    @Override
    public void setCurrentUserId(String userId) {

    }

    @Override
    public String getCurrentUserName() {
        return null;
    }

    @Override
    public void setCurrentUserName(String name) {

    }

    @Override
    public void setCurrentProfileImage(String url) {

    }

    @Override
    public String getCurrentProfileImage() {
        return null;
    }

    @Override
    public void setCurrentUserEmail(String email) {

    }

    @Override
    public String getCurrentUSerEmail() {
        return null;
    }
}
