package ominext.android.vn.nicepic.ui.login;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * Created by MyPC on 24/07/2017.
 */

public class LoginPresenterImpl<L> implements LoginPresenter<L> {
    LoginView loginView;
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;

    public LoginPresenterImpl(LoginView loginView, FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase, DatabaseReference reference) {
        this.loginView = loginView;
        this.firebaseAuth = firebaseAuth;
        this.firebaseDatabase = firebaseDatabase;
        this.reference = reference;
    }

    @Override
    public void onSignIn(String email, String pass) {

    }
}
