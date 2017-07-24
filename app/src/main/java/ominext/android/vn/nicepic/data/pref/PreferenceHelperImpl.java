package ominext.android.vn.nicepic.data.pref;

import android.content.Context;
import android.content.SharedPreferences;

import javax.inject.Inject;

import ominext.android.vn.nicepic.di.ApplicationContext;

/**
 * Created by bien on 7/17/2017.
 */

public class PreferenceHelperImpl implements PreferenceHelper {
    private static final String PREF_KEY_CURRENT_USER_ID = "PREF_KEY_CURRENT_USER_ID";
    private static final String PREF_KEY_CURRENT_USER_NAME = "PREF_KEY_CURRENT_USER_NAME";
    private static final String PREF_KEY_CURRENT_USER_GENDER = "PREF_KEY_CURRENT_USER_GENDER";
    private static final String PREF_KEY_CURRENT_USER_EMAIL = "PREF_KEY_CURRENT_USER_EMAIL";
    private static final String PREF_KEY_CURRENT_USER_PROFILE_IMAGE = "PREF_KEY_CURRENT_USER_PROFILE_IMAGE";

    private final SharedPreferences mPrefs;

    @Inject
    public PreferenceHelperImpl(@ApplicationContext Context context, String prefName) {
        this.mPrefs = context.getSharedPreferences(prefName,Context.MODE_PRIVATE);
    }

    @Override
    public String getCurrentUserId() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_ID,null);
    }

    @Override
    public void setCurrentUserId(String userId) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_ID,userId).apply();
    }

    @Override
    public String getCurrentUserName() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_NAME,null);
    }

    @Override
    public void setCurrentUserName(String name) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_NAME,name).apply();
    }

    @Override
    public void setCurrentProfileImage(String url) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_PROFILE_IMAGE,url).apply();
    }

    @Override
    public String getCurrentProfileImage() {
        return mPrefs.getString(PREF_KEY_CURRENT_USER_PROFILE_IMAGE,null);
    }

    @Override
    public void setCurrentUserEmail(String email) {
        mPrefs.edit().putString(PREF_KEY_CURRENT_USER_EMAIL,email).apply();
    }




    @Override
    public String getCurrentUSerEmail() {
         return mPrefs.getString(PREF_KEY_CURRENT_USER_EMAIL,null);
    }
}
