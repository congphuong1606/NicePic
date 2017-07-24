package ominext.android.vn.nicepic.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.ui.register.RegisterActivity;

public class LoginActivity extends AppCompatActivity {

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_login_email, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_login_email:
                break;
            case R.id.tv_register:
                startActivity(new Intent(this, RegisterActivity.class));
                finish();
                break;
        }
    }
}
