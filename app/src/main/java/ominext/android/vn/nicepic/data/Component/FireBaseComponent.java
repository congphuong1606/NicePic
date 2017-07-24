package ominext.android.vn.nicepic.data.Component;

import dagger.Component;
import ominext.android.vn.nicepic.data.Module.FireBaseModule;
import ominext.android.vn.nicepic.ui.login.LoginPresenterImpl;
import ominext.android.vn.nicepic.ui.register.RegisPresenterImpl;

/**
 * Created by MyPC on 24/07/2017.
 */
@Component(modules = {FireBaseModule.class})
public interface FireBaseComponent {
    void injectTo(LoginPresenterImpl loginPresenter);

    void injectTo(RegisPresenterImpl regisPresenter);
}
