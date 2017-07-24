package ominext.android.vn.nicepic.Model;

/**
 * Created by MyPC on 24/07/2017.
 */

public class User {
    String userId;
    String userName;
    String userEmail;
    String userAvatar;
    int folow;
    int coutPic;

    public User(String userId, String userName, String userEmail, String userAvatar, int folow, int coutPic) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userAvatar = userAvatar;
        this.folow = folow;
        this.coutPic = coutPic;
    }

    public User() {
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserAvatar() {
        return userAvatar;
    }

    public void setUserAvatar(String userAvatar) {
        this.userAvatar = userAvatar;
    }

    public int getFolow() {
        return folow;
    }

    public void setFolow(int folow) {
        this.folow = folow;
    }

    public int getCoutPic() {
        return coutPic;
    }

    public void setCoutPic(int coutPic) {
        this.coutPic = coutPic;
    }
}
