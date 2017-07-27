package ominext.android.vn.nicepic.ui.register;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ominext.android.vn.nicepic.MyApplication;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.di.module.ViewModule;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
import ominext.android.vn.nicepic.ui.login.LoginActivity;
import ominext.android.vn.nicepic.utils.UtilData;

public class RegisterActivity extends BaseActivity implements RegisterView {

    @BindView(R.id.edt_input_username)
    EditText edtInputUsername;
    @BindView(R.id.edt_input_email)
    EditText edtInputEmail;
    @BindView(R.id.edt_input_pass)
    EditText edtInputPass;
    @BindView(R.id.btn_regis)
    Button btnRegis;
    @BindView(R.id.btn_reset_password)
    TextView btnResetPassword;
    @BindView(R.id.btn_back_login)
    Button btnBackLogin;
    @Inject
    RegisPresenter regisPresenter;
    private String useName;
    private String email;
    private String pass;

    @Override
    public void initContentview() {
        setContentView(R.layout.activity_register);
    }

    @Override
    public void injectDependence() {
        MyApplication.get().getComponent()
                .plus(new ViewModule(this)).injectTo(this);

    }

    @Override
    public void binView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {

    }



    @Override
    public void onRegisSuccess() {
        edtInputPass.setText("");
        edtInputUsername.setText("");
        edtInputEmail.setText("");
        hideLoading();
        onShowBuider(getResources().getString(R.string.verifiation));


    }

    @Override
    public void onRegisFail(String msg) {
        hideLoading();
        onShowBuider(msg);
    }

    @Override
    public boolean onCheckInput() {
        if (UtilData.isEmpty(edtInputEmail) && UtilData.isEmpty(edtInputUsername)
                &&UtilData.isEmpty(edtInputPass)) {
            useName=edtInputUsername.getText().toString().trim();
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


    @OnClick({R.id.btn_regis, R.id.btn_reset_password, R.id.btn_back_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_regis:
                if(onCheckInput()){
                    showLoading();
                    regisPresenter.onSignUp(useName, email, pass,this);
                }
                break;
            case R.id.btn_reset_password:
                break;
            case R.id.btn_back_login:
                finish();
                startActivity(new Intent(this, LoginActivity.class));
                break;
        }
    }
}
