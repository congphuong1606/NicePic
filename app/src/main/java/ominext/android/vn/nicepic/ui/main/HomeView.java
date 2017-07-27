package ominext.android.vn.nicepic.ui.main;

import java.util.ArrayList;

import ominext.android.vn.nicepic.data.model.Pic;

/**
 * Created by MyPC on 26/07/2017.
 */

public interface HomeView {
    void loadPicsSuccess(ArrayList<Pic> pics);
    void loadTopPicsSuccess(ArrayList<Pic> topPics);
}
