package ominext.android.vn.nicepic.di.component;

import dagger.Component;
import ominext.android.vn.nicepic.di.PresenterActivity;
import ominext.android.vn.nicepic.di.module.ActivityModule;
import ominext.android.vn.nicepic.ui.login.LoginActivity;
import ominext.android.vn.nicepic.ui.register.RegisterActivity;

/**
 * Created by MyPC on 24/07/2017.
 */
@PresenterActivity
@Component (dependencies = ApplicationComponent.class,modules = ActivityModule.class)
public interface ActivityComponent {
    void inject(LoginActivity loginActivity);
    void inject(RegisterActivity registerActivity);
}
