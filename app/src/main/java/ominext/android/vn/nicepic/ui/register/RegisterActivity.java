package ominext.android.vn.nicepic.ui.register;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.ui.login.LoginActivity;
import ominext.android.vn.nicepic.utils.UtilData;

public class RegisterActivity extends AppCompatActivity implements RegisterView {
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
    String name;
    String email;
    String pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);


    }

    @Override
    public void onRegisSuccess() {
        edtInputPass.setText("");
        edtInputUsername.setText("");
        edtInputEmail.setText("");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");
        builder.setMessage(getResources().getString(R.string.verifiation));
        builder.setIcon(R.drawable.logo);
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
        builder.create().show();
    }

    @Override
    public void onRegisFail(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCheckInput(String name,String email,String pass) {
        if (UtilData.isEmpty(edtInputEmail) && UtilData.isEmpty(edtInputPass) && UtilData.isEmpty(edtInputUsername)) {
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
                name = edtInputEmail.getText().toString().trim();
                email = edtInputUsername.getText().toString().trim();
                pass = edtInputPass.getText().toString().trim();
//                regisPresenter.onSignUp(name, email, pass);
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
