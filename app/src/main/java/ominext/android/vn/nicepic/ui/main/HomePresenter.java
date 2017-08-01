package ominext.android.vn.nicepic.ui.main;

import android.content.SharedPreferences;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import javax.inject.Inject;

import ominext.android.vn.nicepic.data.model.Pic;
import ominext.android.vn.nicepic.utils.Constants;

/**
 * Created by MyPC on 26/07/2017.
 */

public class HomePresenter {
    HomeView homeView;
    private FirebaseDatabase mDatabase;
    private SharedPreferences mPreferences;
    DatabaseReference mReference;
    DatabaseReference getReference;



    @Inject
    public HomePresenter(HomeView homeView, FirebaseDatabase mDatabase,
                         DatabaseReference mReference, SharedPreferences mPreferences) {
        this.homeView = homeView;
        this.mDatabase = mDatabase;
        this.mReference = mReference;
        this.mPreferences = mPreferences;
    }

    public void loadDataPic(final ArrayList<Pic> pics) {
      //  = getReference.child(Constants.PICS_PATH);
        DatabaseReference ref = mReference.child(Constants.PICS_PATH);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    pics.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Pic pic=snapshot.getValue(Pic.class);
                        pics.add(pic);

                    }
                    homeView.loadPicsSuccess(pics);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }


    public void loadDataTopPic(ArrayList<Pic> topPics) {
        DatabaseReference a= mReference.child(Constants.PICS_PATH);
        Query query=a.orderByChild(Constants.PIC_LIKE).limitToLast(3);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    topPics.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Pic pic=snapshot.getValue(Pic.class);
                        topPics.add(pic);
                    }
                    homeView.loadTopPicsSuccess(topPics);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });

    }
}
