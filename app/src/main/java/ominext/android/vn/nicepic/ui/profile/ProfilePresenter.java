package ominext.android.vn.nicepic.ui.profile;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import javax.inject.Inject;

import ominext.android.vn.nicepic.utils.Constants;

/**
 * Created by MyPC on 26/07/2017.
 */

public class ProfilePresenter {
    private FirebaseAuth mAuth;
    private FirebaseUser mUser;
    private DatabaseReference mDatabaseReference;
    private SharedPreferences mPreferences;
    private FirebaseStorage mStorage;
    private StorageReference mStorageReference;
    ProfileView profileView;

    @Inject
    public ProfilePresenter(FirebaseAuth mAuth, FirebaseUser mUser,
                            DatabaseReference mReference, SharedPreferences mPreferences,
                            FirebaseStorage mStorage, ProfileView profileView,StorageReference mStorageReference) {
        this.mAuth = mAuth;
        this.mUser = mUser;
        this.mDatabaseReference = mReference;
        this.mStorageReference=mStorageReference;
        this.mPreferences = mPreferences;
        this.mStorage = mStorage;
        this.profileView = profileView;
    }

    public void loadData() {
        DatabaseReference reference = mDatabaseReference.child(Constants.USERS_PATH)
                .child(mPreferences.getString(Constants.PREF_USER_ID, ""));

    }

    public void onLogOut() {
        mAuth.signOut();
        profileView.onLogOutSuccess();
    }

    public void uploadUserAvatar(byte[] b) {
        String id=mPreferences.getString(Constants.PREF_USER_ID,"");
        StorageReference mountainImagesRef = mStorageReference.child(Constants.IMAGE_AVATAR_PATH).child(id).child("avatar.jpg");
        UploadTask uploadTask = mountainImagesRef.putBytes(b);
            uploadTask.addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception exception) {
                    profileView.onUpLoadAvatarFail(String.valueOf(exception));
                }
            }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String downloadUrl = String.valueOf(taskSnapshot.getDownloadUrl());
                    updateAvatarUser(downloadUrl);
                }
            });
    }

    private void updateAvatarUser(String downloadUrl) {
        mDatabaseReference.child(Constants.USERS_PATH)
                .child(mPreferences.getString(Constants.PREF_USER_ID,""))
                .child("userAvatar").setValue(downloadUrl);
        profileView.onUploadAvatarSuccess(downloadUrl);
    }
}
