package ominext.android.vn.nicepic.data.Component;

import dagger.Subcomponent;
import ominext.android.vn.nicepic.data.Module.ViewModule;
import ominext.android.vn.nicepic.ui.login.LoginActivity;
import ominext.android.vn.nicepic.ui.register.RegisterActivity;

/**
 * Created by MyPC on 24/07/2017.
 */
@Subcomponent (modules = ViewModule.class)
public interface ViewComponent {
    void injectTo(LoginActivity loginActivity);
    void injectTo(RegisterActivity registerActivity);
}
