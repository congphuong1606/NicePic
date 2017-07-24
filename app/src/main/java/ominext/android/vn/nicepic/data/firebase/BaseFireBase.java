package ominext.android.vn.nicepic.data.firebase;

import android.net.Uri;

import ominext.android.vn.nicepic.data.model.User;

/**
 * Created by MyPC on 24/07/2017.
 */

public interface BaseFireBase {
    void creatUserDatabase(User user);
    void UploadPic(Uri uri);

}
