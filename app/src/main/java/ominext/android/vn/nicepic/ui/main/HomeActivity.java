package ominext.android.vn.nicepic.ui.main;

import android.content.SharedPreferences;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SearchView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Handler;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import me.relex.circleindicator.CircleIndicator;
import ominext.android.vn.nicepic.MyApplication;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.data.model.Pic;
import ominext.android.vn.nicepic.di.module.ViewModule;
import ominext.android.vn.nicepic.ui.Adapter.PicsAdapter;
import ominext.android.vn.nicepic.ui.Adapter.TopPicAdapter;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
import ominext.android.vn.nicepic.ui.newpic.NewPicActivity;
import ominext.android.vn.nicepic.ui.profile.ProfileActivity;
import ominext.android.vn.nicepic.utils.Constants;

public class HomeActivity extends BaseActivity implements HomeView {
    @BindView(R.id.rcv_group)
    RecyclerView rcvGroup;
    @BindView(R.id.searchview)
    SearchView searchview;
    @BindView(R.id.btn_creat_new_chat)
    ImageButton btnCreatNewChat;
    @BindView(R.id.ic_me)
    CircleImageView icMe;
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;
    @BindView(R.id.viewPager)
    ViewPager viewPager;
    @BindView(R.id.indicator)
    CircleIndicator indicator;
    //
    ArrayList<Pic> pics = new ArrayList<>();
    ArrayList<Pic> topPics = new ArrayList<>();
    PicsAdapter picHomeAdapter;
    TopPicAdapter topPicAdapter;
    //
    private static int currentPage = 0;
    //
    RecyclerView.LayoutManager layoutManager;
    @Inject
    SharedPreferences preferences;
    @Inject
    HomePresenter homePresenter;
    @Override
    public void initContentview() {
        setContentView(R.layout.activity_home);

    }
    @Override
    public void injectDependence() {
        MyApplication.get().getComponent().plus(new ViewModule(this)).injectTo(this);
    }
    @Override
    public void binView() {
        ButterKnife.bind(this);
    }

    @Override
    public void initData() {
        String avatar = preferences.getString(Constants.PREF_USER_AVATAR, "");
        Glide.with(getApplicationContext()).load(avatar).into(icMe);
        layoutManager = new GridLayoutManager(this, 2);
        rcvGroup.setLayoutManager(layoutManager);
        rcvGroup.setHasFixedSize(true);
        homePresenter.loadDataTopPic(topPics);
        homePresenter.loadDataPic(pics);
    }

    @OnClick({R.id.btn_creat_new_chat, R.id.ic_me})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_creat_new_chat:
                onStartActivity(NewPicActivity.class);
                break;
            case R.id.ic_me:
                onStartActivity(ProfileActivity.class);
                break;
        }
    }

    @Override
    public void loadPicsSuccess(ArrayList<Pic> pics) {
        picHomeAdapter = new PicsAdapter(pics);
        rcvGroup.setAdapter(picHomeAdapter);
    }

    @Override
    public void loadTopPicsSuccess(ArrayList<Pic> topPics) {
        viewPager.setAdapter(new TopPicAdapter(this, topPics));
        indicator.setViewPager(viewPager);
        final android.os.Handler handler=new android.os.Handler();
        final Runnable runnable=new Runnable() {
            @Override
            public void run() {
                if(currentPage==3){
                    currentPage=0;
                }viewPager.setCurrentItem(currentPage++,true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, 1500, 1500);
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }

            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }

            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });
    }


}
