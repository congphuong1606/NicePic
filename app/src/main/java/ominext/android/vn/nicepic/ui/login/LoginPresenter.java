package ominext.android.vn.nicepic.ui.login;

/**
 * Created by MyPC on 24/07/2017.
 */

public interface LoginPresenter<L> {
    void onSignIn(String email, String pass);
}
