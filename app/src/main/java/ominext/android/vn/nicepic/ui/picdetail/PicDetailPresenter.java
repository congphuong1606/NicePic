package ominext.android.vn.nicepic.ui.picdetail;

import android.content.SharedPreferences;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import javax.inject.Inject;

import ominext.android.vn.nicepic.data.model.Comment;
import ominext.android.vn.nicepic.data.model.Pic;
import ominext.android.vn.nicepic.utils.Constants;

/**
 * Created by MyPC on 28/07/2017.
 */

public class PicDetailPresenter {
    PicDetailView picDetailView;
    private DatabaseReference mDatabaseReference;
    private Integer picLike;

    private SharedPreferences mPreferences;


    @Inject
    public PicDetailPresenter(SharedPreferences mPreferences,
                              PicDetailView picDetailView,
                              DatabaseReference mDatabaseReference) {
        this.mPreferences=mPreferences;
        this.picDetailView = picDetailView;
        this.mDatabaseReference = mDatabaseReference;
    }


    public void onUpdateDonateLike(String picId, int sttLike) {
        DatabaseReference mRefPic = mDatabaseReference
                .child(Constants.PICS_PATH).child(picId).child(Constants.PIC_LIKE);
        Query query = mDatabaseReference.child(Constants.PICS_PATH)
                .orderByChild(Constants.PIC_ID).equalTo(picId);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Pic pic = dataSnapshot.getValue(Pic.class);
                picLike = pic.getPicLike();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        if (sttLike % 2 == 0) {
            picLike++;
        } else picLike--;
        mRefPic.setValue(picLike);
        picDetailView.onUpdatePicLikeSuccess(picLike);
    }

    public void onUploadCmt(String cmtContent, String picId) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        //
        String cmtId = picId + String.valueOf(System.currentTimeMillis());
        String cmtTime = format.format(Calendar.getInstance().getTime());
        String cmtPic=picId;
        String cmtUser = mPreferences.getString(Constants.PREF_USER_ID, "");
        String cmtAvatarUser=mPreferences.getString(Constants.PREF_USER_AVATAR,"");
        //
        Comment comment=new Comment(cmtId,cmtContent,cmtTime,cmtPic,cmtUser,cmtAvatarUser,0);
        mDatabaseReference.child(Constants.CMTS_PATH).child(cmtId).setValue(comment);
        picDetailView.upLoadCmtSuccess();

    }

    public void onLoadDataCmts(ArrayList<Comment> comments, String picId) {
        DatabaseReference ref = mDatabaseReference.child(Constants.CMTS_PATH);
        Query query=ref.orderByChild(Constants.CMT_PIC).equalTo(picId);
        query.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot!=null){
                    comments.clear();
                    for (DataSnapshot snapshot:dataSnapshot.getChildren()){
                        Comment comment=snapshot.getValue(Comment.class);
                        comments.add(comment);

                    }
                    picDetailView.loadCmtsSuccess(comments);
                }

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
