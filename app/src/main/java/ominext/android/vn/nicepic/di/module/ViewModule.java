package ominext.android.vn.nicepic.di.module;

import dagger.Module;
import dagger.Provides;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
import ominext.android.vn.nicepic.ui.login.LoginView;
import ominext.android.vn.nicepic.ui.main.HomeView;
import ominext.android.vn.nicepic.ui.newpic.NewPicView;
import ominext.android.vn.nicepic.ui.picdetail.PicDetailView;
import ominext.android.vn.nicepic.ui.profile.ProfileView;
import ominext.android.vn.nicepic.ui.register.RegisterView;

/**
 * Created by MyPC on 25/07/2017.
 */
@Module
public class ViewModule {
    BaseActivity baseActivity;
    LoginView loginView;
    RegisterView registerView;
    ProfileView profileView;
    HomeView homeView;
    NewPicView newPicView;
    PicDetailView picDetailView;

    public ViewModule(LoginView loginView, RegisterView registerView,
                      ProfileView profileView, HomeView homeView,
                      NewPicView newPicView, PicDetailView picDetailView) {
        this.loginView = loginView;
        this.registerView = registerView;
        this.profileView = profileView;
        this.homeView = homeView;
        this.newPicView = newPicView;
        this.picDetailView = picDetailView;
    }

    public ViewModule(BaseActivity baseActivity) {
        this.baseActivity=baseActivity;

    }


    @Provides
    public NewPicView getNewPicView() {
        return newPicView;
    }

    @Provides
    public LoginView getLoginView() {
        return loginView;
    }

    @Provides
    public RegisterView getRegisterView() {
        return registerView;
    }

    @Provides
    public ProfileView getProfileView() {
        return profileView;
    }

    @Provides
    public HomeView getHomeView() {
        return homeView;
    }
}
