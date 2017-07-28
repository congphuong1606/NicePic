package ominext.android.vn.nicepic.di.component;

import dagger.Subcomponent;
import ominext.android.vn.nicepic.di.module.StorageModule;
import ominext.android.vn.nicepic.di.module.ViewModule;
import ominext.android.vn.nicepic.ui.login.LoginActivity;
import ominext.android.vn.nicepic.ui.main.HomeActivity;
import ominext.android.vn.nicepic.ui.newpic.NewPicActivity;
import ominext.android.vn.nicepic.ui.picdetail.PicDetailActivity;
import ominext.android.vn.nicepic.ui.register.RegisterActivity;

/**
 * Created by MyPC on 25/07/2017.
 */
@Subcomponent(modules = {ViewModule.class, StorageModule.class})
public interface ViewComponent {
    void injectTo(LoginActivity activity);
    void injectTo(HomeActivity activity);
    void injectTo(RegisterActivity activity);
    void injectTo(NewPicActivity activity);
    void injectTo(PicDetailActivity activity);
}
