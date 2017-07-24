package ominext.android.vn.nicepic.data;

/**
 * Created by MyPC on 25/07/2017.
 */

public interface DatabaseManager {
    String getCurrentUserId();
    void setCurrentUserId(String userId);

    String getCurrentUserName();
    void setCurrentUserName(String name);

    void setCurrentProfileImage(String url);
    String getCurrentProfileImage();

    void setCurrentUserEmail(String email);
    String getCurrentUSerEmail();
}
