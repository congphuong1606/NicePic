package ominext.android.vn.nicepic.data.Module;

import dagger.Module;
import dagger.Provides;
import ominext.android.vn.nicepic.ui.login.LoginView;
import ominext.android.vn.nicepic.ui.register.RegisterView;

/**
 * Created by MyPC on 24/07/2017.
 */
@Module
public class ViewModule {
    LoginView loginView;
    RegisterView registerView;
    public ViewModule(RegisterView registerView) {
        this.registerView = registerView;
    }
    public ViewModule(LoginView loginView) {
        this.loginView = loginView;
    }
    @Provides
    public RegisterView getRegisterView() {
        return registerView;
    }
    @Provides
    public LoginView getLoginView() {
        return loginView;
    }
}

