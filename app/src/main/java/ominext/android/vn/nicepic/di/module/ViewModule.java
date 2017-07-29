package ominext.android.vn.nicepic.di.module;

import dagger.Module;
import dagger.Provides;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
import ominext.android.vn.nicepic.ui.login.LoginActivity;
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
       LoginView loginView;
    RegisterView registerView;
    ProfileView profileView;
    HomeView homeView;
    NewPicView newPicView;
    PicDetailView picDetailView;

    public ViewModule(LoginView loginView) {
        this.loginView = loginView;
    }

    public ViewModule(RegisterView registerView) {
        this.registerView = registerView;
    }

    public ViewModule(ProfileView profileView) {
        this.profileView = profileView;
    }

    public ViewModule(HomeView homeView) {
        this.homeView = homeView;
    }

    public ViewModule(NewPicView newPicView) {
        this.newPicView = newPicView;
    }

    public ViewModule(PicDetailView picDetailView) {
        this.picDetailView = picDetailView;
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
