package ominext.android.vn.nicepic.ui.picdetail;

import javax.inject.Inject;

/**
 * Created by MyPC on 28/07/2017.
 */

public class PicDetailPresenter {
    PicDetailView picDetailView;

    @Inject
    public PicDetailPresenter(PicDetailView picDetailView) {
        this.picDetailView = picDetailView;
    }

    public void onUpdatePicDetailActivity() {

    }
}
