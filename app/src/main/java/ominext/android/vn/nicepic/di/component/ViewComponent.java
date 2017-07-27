package ominext.android.vn.nicepic.di.component;

import dagger.Subcomponent;
import ominext.android.vn.nicepic.di.module.StorageModule;
import ominext.android.vn.nicepic.di.module.ViewModule;
import ominext.android.vn.nicepic.ui.login.LoginActivity;
import ominext.android.vn.nicepic.ui.main.HomeActivity;
import ominext.android.vn.nicepic.ui.newpic.NewPicActivity;
import ominext.android.vn.nicepic.ui.profile.ProfileActivity;
import ominext.android.vn.nicepic.ui.register.RegisterActivity;

/**
 * Created by MyPC on 25/07/2017.
 */
@Subcomponent(modules = {ViewModule.class, StorageModule.class})
public interface ViewComponent {
    void injectTo(LoginActivity loginActivity);
    void injectTo(RegisterActivity registerActivity);
    void injectTo(ProfileActivity profileActivity);
    void injectTo(HomeActivity homeActivity);
    void injectTo(NewPicActivity newPicActivity);
}
