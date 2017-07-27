package ominext.android.vn.nicepic.ui.login;

import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import javax.inject.Inject;

import ominext.android.vn.nicepic.data.model.User;
import ominext.android.vn.nicepic.utils.Constants;

import static android.content.ContentValues.TAG;

/**
 * Created by MyPC on 24/07/2017.
 */

public class LoginPresenter {
    String nameUser;
    String avatarUser;
    private LoginView loginView;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private SharedPreferences.Editor mPeditor;
    private DatabaseReference reference;
    private FirebaseUser curentUser;



    @Inject
    public LoginPresenter(final LoginView loginView, FirebaseAuth firebaseAuth,DatabaseReference reference,
                          SharedPreferences.Editor mPeditor) {
        this.loginView = loginView;
        this.mAuth = firebaseAuth;
        this.mPeditor = mPeditor;
        this.reference = reference;

    }

    public void onSignIn(String email, String pass) {
        mAuth.signInWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            curentUser = mAuth.getCurrentUser();
                            checkVeriAccount();
                            setPref();
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i(TAG, "onFailure: " + e);
                        if (String.valueOf(e).equals("com.google.firebase.auth.FirebaseAuthInvalidUserException: There is no user record corresponding to this identifier. The user may have been deleted."))
                            ;
                        loginView.onSignInFail("email hoặc mật khẩu không đúng");
                    }
                });
    }

    private void setPref() {

        final String idUser = curentUser.getUid();
        final String email = curentUser.getEmail();
        DatabaseReference ref = reference.child(Constants.USERS_PATH).child(idUser);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User user = dataSnapshot.getValue(User.class);
                nameUser = user.getUserName();
                avatarUser = user.getUserAvatar();
                mPeditor.putString(Constants.PREF_USER_ID, idUser)
                        .putString(Constants.PREF_USER_AVATAR, avatarUser)
                        .putString(Constants.PREF_USER_EMAIL, email)
                        .putString(Constants.PREF_USER_NAME,nameUser)
                        .commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }

    public void checkVeriAccount() {
        if (curentUser != null) {
            if (curentUser.isEmailVerified()) {
                loginView.onSignInSuccess();
            } else {
                mAuth.signOut();
                loginView.onViriFail();

            }
        }
    }
}
