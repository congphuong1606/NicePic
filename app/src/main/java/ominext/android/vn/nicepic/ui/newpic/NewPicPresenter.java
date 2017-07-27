package ominext.android.vn.nicepic.ui.newpic;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.inject.Inject;

import ominext.android.vn.nicepic.data.model.Pic;
import ominext.android.vn.nicepic.utils.Constants;

/**
 * Created by MyPC on 26/07/2017.
 */

public class NewPicPresenter {
    NewPicView newPicView;
    FirebaseDatabase mDatabase;
    FirebaseStorage mStorage;
    StorageReference mStorageReference;
    DatabaseReference mReference;
    SharedPreferences mPreferences;
    //
    String picId;
    String picUser;
    String picName;
    String picDescription;
    String picTimeUpdate;

    @Inject
    public NewPicPresenter(StorageReference mStorageReference,
                           NewPicView newPicView, FirebaseDatabase mDatabase,
                           FirebaseStorage mStorage, DatabaseReference mReference,
                           SharedPreferences mPreferences) {
        this.mStorageReference = mStorageReference;
        this.newPicView = newPicView;
        this.mDatabase = mDatabase;
        this.mStorage = mStorage;
        this.mReference = mReference;
        this.mPreferences = mPreferences;
    }

    void uploadPic(byte[] iByte, String picName, String picDescription) {
        this.picName=picName;
        this.picDescription=picDescription;
        this.picId = mPreferences.getString(Constants.PREF_USER_ID, "") + String.valueOf(System.currentTimeMillis());
        StorageReference mSto = mStorageReference.child(Constants.IMAGE_PIC_PATH).child(picId + ".jpg");
        UploadTask uploadTask = mSto.putBytes(iByte);
        uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                String picUrl = String.valueOf(taskSnapshot.getDownloadUrl());
                creatPicDatabase(picUrl);

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                newPicView.onUpPicFail(String.valueOf(exception));
            }
        });
    }

    private void creatPicDatabase(String picUrl) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        picTimeUpdate = format.format(Calendar.getInstance().getTime());
         //String
        picUser = mPreferences.getString(Constants.PREF_USER_ID, "");
        Pic pic = new Pic(picId, picUser, picUrl, picName, picDescription, picTimeUpdate,0,0);
        mReference.child(Constants.PICS_PATH).child(picId).setValue(pic);
        newPicView.upLoadSuccess();



    }
}
