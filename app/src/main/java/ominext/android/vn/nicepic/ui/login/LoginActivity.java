package ominext.android.vn.nicepic.ui.login;

import android.content.SharedPreferences;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ominext.android.vn.nicepic.MyApplication;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.di.module.ViewModule;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
import ominext.android.vn.nicepic.ui.main.HomeActivity;
import ominext.android.vn.nicepic.ui.register.RegisterActivity;
import ominext.android.vn.nicepic.utils.Constants;
import ominext.android.vn.nicepic.utils.UtilData;

public class LoginActivity extends BaseActivity implements LoginView {
    @BindView(R.id.edt_input_email)
    EditText edtInputEmail;
    @BindView(R.id.edt_input_pass)
    EditText edtInputPass;
    @BindView(R.id.btn_login_email)
    Button btnLoginEmail;
    @BindView(R.id.btn_fb_login)
    Button btnFbLogin;
    @BindView(R.id.tv_register)
    Button tvRegister;
    @BindView(R.id.rempasswordcheckbox)
    CheckBox rempasswordcheckbox;

    private String email;
    private String pass;
    @Inject
    LoginPresenter loginPresenter;
    @Inject
    SharedPreferences preferences;
    @Inject
    SharedPreferences.Editor editor;
    @Override
    public void injectDependence() {
        MyApplication.get().getComponent()
                .plus(new ViewModule(this)).injectTo(this);

    }

    @Override
    public void initContentview() {
        setContentView(R.layout.activity_login);
    }

    @Override
    public void binView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        edtInputEmail.setText(preferences
                .getString(Constants.LOGIN_NAME, ""));
        edtInputPass.setText(preferences
                .getString(Constants.LOGIN_PASS, ""));
        if(!preferences.getString(Constants.LOGIN_NAME,"").isEmpty()){
            rempasswordcheckbox.setChecked(true);
        }
    }

    @OnClick({R.id.btn_login_email,
            R.id.btn_fb_login,
            R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_email:
                if (isCheckInputData()) {
                    showLoading();
                    loginPresenter.onSignIn(email, pass);
                }
                break;
            case R.id.btn_fb_login:
                break;
            case R.id.tv_register:
                onStartActivity(RegisterActivity.class);
                break;
        }
    }

    @Override
    public void onSignInSuccess() {
        if (rempasswordcheckbox.isChecked()) {
            editor.putString(Constants.LOGIN_NAME, email)
                    .putString(Constants.LOGIN_PASS, pass)
                    .commit();
        } else {
            editor.clear().commit();
        }
        hideLoading();
        onStartActivity(HomeActivity.class);
    }

    @Override
    public void onSignInFail(String msg) {
        hideLoading();
        onShowBuider(msg);
    }

    @Override
    public void onViriFail() {
        hideLoading();
        onShowBuider(getResources().getString(R.string.notVeriaccount));
    }

    @Override
    public boolean isCheckInputData() {
        if (UtilData.isEmpty(edtInputEmail)
                && UtilData.isEmpty(edtInputPass)) {
            email = edtInputEmail.getText().toString().trim();
            pass = edtInputPass.getText().toString().trim();
            if (!UtilData.isEmailValid(email)) {
                edtInputEmail.requestFocus();
                edtInputEmail.setError(getResources().getString(R.string.email_error));
                return false;
            } else {
                if (pass.length() < 6) {
                    edtInputPass.requestFocus();
                    edtInputPass.setError(getResources().getString(R.string.pass_error));
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }


}


