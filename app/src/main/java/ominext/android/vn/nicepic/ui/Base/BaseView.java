package ominext.android.vn.nicepic.ui.Base;

/**
 * Created by MyPC on 24/07/2017.
 */

public interface BaseView {
    void showLoading();

    void hideLoading();

    void showError(String message);

    void showError(int message);

    void showToast(String message);

    void showToast(int message);

    void hideKeyboard();
}
