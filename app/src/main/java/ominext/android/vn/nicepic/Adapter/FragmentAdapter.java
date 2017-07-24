//package ominext.android.vn.nicepic.Adapter;
//
//import android.support.v4.app.Fragment;
//import android.support.v4.app.FragmentManager;
//import android.support.v4.app.FragmentPagerAdapter;
//
//import ominext.android.vn.nicepic.Fragment.ProfileFragment;
//import ominext.android.vn.nicepic.Fragment.SocialFragment;
//import ominext.android.vn.nicepic.Fragment.UploadFragment;
//
///**
// * Created by MyPC on 10/07/2017.
// */
//
//public class FragmentAdapter extends FragmentPagerAdapter {
//    public FragmentAdapter(FragmentManager fm) {
//        super(fm);
//    }
//
//    @Override
//    public Fragment getItem(int position) {
//        switch (position) {
//            case 0:
//                return ProfileFragment.newInstance();
//            case 1:
//                return SocialFragment.newInstance();
//
//            case 2:
//                return UploadFragment.newInstance();
//
//        }
//
//        return null;
//    }
//
//    @Override
//    public int getCount() {
//        return 3;
//    }
//}
