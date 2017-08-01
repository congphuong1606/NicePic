package ominext.android.vn.nicepic.ui.picdetail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import ominext.android.vn.nicepic.MyApplication;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.data.model.Comment;
import ominext.android.vn.nicepic.data.model.Pic;
import ominext.android.vn.nicepic.di.module.ViewModule;
import ominext.android.vn.nicepic.ui.Adapter.CmtAdapter;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
import ominext.android.vn.nicepic.ui.main.HomeActivity;
import ominext.android.vn.nicepic.utils.Constants;

public class PicDetailActivity extends BaseActivity implements PicDetailView {

    @BindView(R.id.imv_pic)
    ImageView imvPic;
    @BindView(R.id.tv_pic_name)
    TextView tvPicName;
    @BindView(R.id.tv_pic_description)
    TextView tvPicDescription;
    @BindView(R.id.tv_pic_like)
    TextView tvPicLike;
    @BindView(R.id.tv_pic_comment)
    TextView tvPicComment;
    @BindView(R.id.imv_cmt_click)
    ImageView imvCmtClick;
    @BindView(R.id.tv_pic_share)
    TextView tvPicShare;
    @BindView(R.id.rcv_pic_cmts)
    RecyclerView rcvPicCmts;
    @BindView(R.id.imv_avatar)
    CircleImageView imvAvatar;
    @BindView(R.id.edt_new_cmt)
    EditText edtNewCmt;
    @BindView(R.id.imb_send_new_cmt)
    ImageButton imbSendNewCmt;
    @BindView(R.id.imv_heart)
    ImageView imvHeart;
    @BindView(R.id.layout_comment)
    RelativeLayout layoutComment;
    @BindView(R.id.btn_back)
    Button btnBack;
    private Pic pic;
    //
    ArrayList<Comment> comments = new ArrayList<>();
    //
    int statuslike = 0;
    //
    @Inject
    SharedPreferences mPref;
    @Inject
    PicDetailPresenter mPresenter;
    private String picId;
    private int picLike;
    private CmtAdapter cmtAdapter;



    @Override
    public void initContentview() {
        setContentView(R.layout.activity_pic_detail);
    }

    @Override
    public void injectDependence() {
        MyApplication.get().getComponent().plus(new ViewModule(this)).injectTo(this);

    }

    @Override
    public void binView() {
        ButterKnife.bind(this);
        layoutComment.setVisibility(View.GONE);


    }

    @Override
    public void initData() {
        Bundle b = this.getIntent().getBundleExtra("bundlePic");
        pic = (Pic) b.getSerializable("picDetail");
        picId = pic.getPicId();
        picLike = pic.getPicLike();
        tvPicComment.setText(pic.getPicCmt() + " comment");
        tvPicLike.setText(picLike + " like");
        tvPicName.setText(pic.getPicName() + "");
        tvPicDescription.setText((pic.getPicDescription() + ""));
        Glide.with(getApplicationContext()).load(pic.getPicUrl()).into(imvPic);
        String myAvatar = mPref.getString(Constants.PREF_USER_AVATAR, "");
        Glide.with(getApplicationContext()).load(myAvatar).into(imvAvatar);
        //
        initDataRecycleView();
    }

    private void initDataRecycleView() {
        rcvPicCmts.setLayoutManager(new GridLayoutManager(this, 1));
        rcvPicCmts.setHasFixedSize(true);
        mPresenter.onLoadDataCmts(comments, picId);
    }
    @Override
    public void onUpdatePicDetailSuccess() {
    }
    @Override
    public void onFail(String msg) {

    }

    @Override
    public void onUpdatePicLikeSuccess(int picCurentLike) {
        tvPicLike.setText(picCurentLike + " like");
    }

    @Override
    public void upLoadCmtSuccess() {
        edtNewCmt.setText("");
        cmtAdapter.notifyDataSetChanged();
    }

    @Override
    public void loadCmtsSuccess(ArrayList<Comment> comments) {
        cmtAdapter = new CmtAdapter(comments,pic);
        rcvPicCmts.setAdapter(cmtAdapter);
    }
    @OnClick({R.id.imv_pic, R.id.imv_cmt_click, R.id.imb_send_new_cmt,
            R.id.imv_heart,R.id.btn_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_pic:
                break;
            case R.id.btn_back:
                onStartActivity(HomeActivity.class);
                finish();
                break;
            case R.id.imv_cmt_click:
                layoutComment.setVisibility(View.VISIBLE);
                break;
            case R.id.imb_send_new_cmt:

                layoutComment.setVisibility(View.GONE);
                String cmtContent = edtNewCmt.getText().toString();
                mPresenter.onUploadCmt(cmtContent, picId);
                break;
            case R.id.imv_heart:
                statuslike++;
                if (imvPic != null) {
                    mPresenter.onUpdateDonateLike(picId, statuslike);
                }
                break;
        }
    }



}
