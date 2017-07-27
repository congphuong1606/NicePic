package ominext.android.vn.nicepic.ui.login;

/**
 * Created by MyPC on 24/07/2017.
 */

public interface LoginView {
    void onSignInSuccess();

    void onSignInFail(String msg);
    void onViriFail();
    boolean isCheckInputData();
}
