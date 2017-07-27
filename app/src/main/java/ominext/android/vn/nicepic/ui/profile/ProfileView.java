package ominext.android.vn.nicepic.ui.profile;

/**
 * Created by MyPC on 26/07/2017.
 */

public interface ProfileView {
    void loadDataSuccess();
    void onLogOutSuccess();
    void onUpLoadAvatarFail(String msg);
    void onUploadAvatarSuccess(String downloadUrl);
}
