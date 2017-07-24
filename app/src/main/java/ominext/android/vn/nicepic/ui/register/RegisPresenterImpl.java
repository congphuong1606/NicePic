package ominext.android.vn.nicepic.ui.register;

import android.app.Activity;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import ominext.android.vn.nicepic.data.model.User;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
import ominext.android.vn.nicepic.utils.Constants;

/**
 * Created by MyPC on 24/07/2017.
 */

public class RegisPresenterImpl<R> extends BaseActivity implements RegisPresenter<R> {
    FirebaseAuth firebaseAuth;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference reference;
    RegisterView registerView;

    public RegisPresenterImpl(FirebaseAuth firebaseAuth, FirebaseDatabase firebaseDatabase, DatabaseReference reference, RegisterView registerView) {
        this.firebaseAuth = firebaseAuth;
        this.firebaseDatabase = firebaseDatabase;
        this.reference = reference;
        this.registerView = registerView;
    }

    @Override
    public void onSignUp(final String useName, final String email, String pass) {
        registerView.onCheckInput(useName,email,pass);
        if (registerView.onCheckInput(useName,email,pass)) {
            showProgressDialog("Vui lòng đợi", ((Activity) registerView));
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
                                            hideProgressDialog();
                                            registerView.onRegisSuccess();
                                        }
                                    });
                                }
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            hideProgressDialog();
                            registerView.onRegisFail(String.valueOf(e));
                        }
                    });
        }

    }

    @Override
    public void onCreadUserDatabase(String username, String email) {
        String id = firebaseAuth.getCurrentUser().getUid();
        User currentUser = new User(id, username, email, null, 0, 0);
        reference.child(Constants.USERS_PATH).child(id).setValue(currentUser);

    }

}
