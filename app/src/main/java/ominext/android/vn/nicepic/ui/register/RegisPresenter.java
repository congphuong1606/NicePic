package ominext.android.vn.nicepic.ui.register;

import android.content.Context;
import android.support.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.StorageReference;

import javax.inject.Inject;

import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.data.model.User;
import ominext.android.vn.nicepic.utils.Constants;

/**
 * Created by MyPC on 24/07/2017.
 */

public class RegisPresenter {
    private FirebaseAuth mAuth;
    private FirebaseDatabase mDatabase;
    private DatabaseReference reference;
    private RegisterView mView;
    StorageReference storageReference;


    @Inject
    public RegisPresenter(FirebaseAuth mAuth,
                          StorageReference storageReference,
                          DatabaseReference reference,
                          FirebaseDatabase mDatabase,
                          RegisterView mView) {
        this.storageReference = storageReference;
        this.mAuth = mAuth;
        this.reference = reference;
        this.mDatabase = mDatabase;
        this.mView = mView;

    }

    public void onSignUp(final String useName, final String email, String pass, final Context context) {
        mAuth.createUserWithEmailAndPassword(email, pass)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            final FirebaseUser userFB = task.getResult().getUser();
                            if (userFB != null) {
                                onCreadUserDatabase(useName, email,context);
                                userFB.sendEmailVerification().addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        mView.onRegisSuccess();
                                    }
                                });
                            }
                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (String.valueOf(e).equals("com.google.firebase.auth.FirebaseAuthUserCollisionException: The email address is already in use by another account.")) {
                            mView.onRegisFail("email đã được sử dụng");
                        } else {
                            mView.onRegisFail("lỗi không xác định");
                        }
                    }
                });


    }

    public void onCreadUserDatabase(String username, String email, Context context) {
        String noAvatar=context.getResources().getString(R.string.noavatar);
        String id = mAuth.getCurrentUser().getUid();
        User currentUser = new User(id, username, email, noAvatar, 0, 0);
        reference.child(Constants.USERS_PATH).child(id).setValue(currentUser);

    }

}
