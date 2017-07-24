package ominext.android.vn.nicepic.ui.register;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import javax.inject.Inject;

import ominext.android.vn.nicepic.Base.BaseActivity;
import ominext.android.vn.nicepic.Model.User;
import ominext.android.vn.nicepic.R;

import static android.R.attr.name;

/**
 * Created by MyPC on 24/07/2017.
 */

public class RegisPresenterImpl extends BaseActivity implements RegisPresenter {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RegisterView registerView;

    @Inject
    public RegisPresenterImpl(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase, DatabaseReference reference, RegisterView registerView) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseDatabase = firebaseDatabase;
        this.reference = reference;
        this.registerView = registerView;
    }

    @Override
    public void onSignUp(final String useName, final String email, String pass) {
        if (registerView.onCheckInput()) {
            showProgressDialog(getResources().getString(R.string.signupin), ((Activity) registerView));
            firebaseAuth.createUserWithEmailAndPassword(email, pass)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                final FirebaseUser userFB = task.getResult().getUser();
                                if (userFB != null) {
                                    onCreadUserDatabase(useName, email);
                                    userFB.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            registerView.onRegisSuccess();
                                        }
                                    });
                                }
                            }
                        }
                    });
        }

    }

    @Override
    public void onCreadUserDatabase(String username, String email) {
        String id=firebaseAuth.getCurrentUser().getUid();
        User currentUser = new User(id,username,email,null,null,null);
        mDatabase.child(Instance.USERS_PATH).child(id).setValue(currentUser);

    }

}
