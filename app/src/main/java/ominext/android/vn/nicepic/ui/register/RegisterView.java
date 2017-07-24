package ominext.android.vn.nicepic.ui.register;

/**
 * Created by MyPC on 24/07/2017.
 */

public interface RegisterView {
    void onRegisSuccess();
    void onRegisFail(String msg);
    boolean onCheckInput();
}
