package ominext.android.vn.nicepic.data.pref;

/**
 * Created by bien on 7/17/2017.
 */

public interface PreferenceHelper {
    String getCurrentUserId();
    void setCurrentUserId(String userId);

    String getCurrentUserName();
    void setCurrentUserName(String name);

    void setCurrentProfileImage(String url);
    String getCurrentProfileImage();

    void setCurrentUserEmail(String email);
    String getCurrentUSerEmail();


}
