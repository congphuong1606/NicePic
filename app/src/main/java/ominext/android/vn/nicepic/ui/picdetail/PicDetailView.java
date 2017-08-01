package ominext.android.vn.nicepic.ui.picdetail;

import java.util.ArrayList;

import ominext.android.vn.nicepic.data.model.Comment;

/**
 * Created by MyPC on 28/07/2017.
 */

public interface PicDetailView {
    void onUpdatePicDetailSuccess();
    void onFail(String msg);
    void onUpdatePicLikeSuccess(int picCurentLike);
    void upLoadCmtSuccess();
    void loadCmtsSuccess(ArrayList<Comment> comments);
}
