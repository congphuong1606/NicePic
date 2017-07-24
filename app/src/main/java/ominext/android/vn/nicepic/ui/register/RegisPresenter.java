package ominext.android.vn.nicepic.ui.register;

/**
 * Created by MyPC on 24/07/2017.
 */

public interface RegisPresenter<R> {
    void onSignUp(String useName, String email, String Pass);
    void onCreadUserDatabase(String userName, String email);
}
