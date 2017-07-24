package ominext.android.vn.nicepic.di.module;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;

import dagger.Module;
import dagger.Provides;
import ominext.android.vn.nicepic.di.ActivityContext;
import ominext.android.vn.nicepic.di.PresenterActivity;
import ominext.android.vn.nicepic.ui.login.LoginPresenter;
import ominext.android.vn.nicepic.ui.login.LoginPresenterImpl;
import ominext.android.vn.nicepic.ui.login.LoginView;
import ominext.android.vn.nicepic.ui.register.RegisPresenter;
import ominext.android.vn.nicepic.ui.register.RegisPresenterImpl;
import ominext.android.vn.nicepic.ui.register.RegisterView;

/**
 * Created by MyPC on 24/07/2017.
 */
@Module
public class ActivityModule {
    private AppCompatActivity appCompatActivity;

    public ActivityModule(AppCompatActivity appCompatActivity) {
        this.appCompatActivity = appCompatActivity;
    }

    @Provides
    public AppCompatActivity provideAppCompatActivity() {
        return appCompatActivity;
    }
    @Provides
    @ActivityContext
    Context provideContext(){
        return appCompatActivity;
    }
    @Provides
    @PresenterActivity
    LoginPresenter<LoginView> provideLoginPresenter(LoginPresenterImpl<LoginView> loginPresenter){
        return loginPresenter;
    }
    @Provides
    @PresenterActivity
    RegisPresenter<RegisterView> provideRegisPresenter(RegisPresenterImpl<RegisterView> regisPresenter){
        return regisPresenter;
    }

}
