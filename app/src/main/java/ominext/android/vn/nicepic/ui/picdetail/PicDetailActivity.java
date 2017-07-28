package ominext.android.vn.nicepic.ui.picdetail;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import ominext.android.vn.nicepic.MyApplication;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.data.model.Pic;
import ominext.android.vn.nicepic.di.module.ViewModule;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
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
    private Pic pic;
    @Inject
    SharedPreferences mPref;

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

    }

    @Override
    public void initData() {
        Bundle b = this.getIntent().getExtras();
        pic = (Pic) b.getSerializable("picDetail");
        String myAvatar=mPref.getString(Constants.PREF_USER_AVATAR,"");

        Glide.with(getApplicationContext()).load(pic.getPicUrl()).into(imvPic);
        tvPicComment.setText(pic.getPicCmt() + " comment");
        tvPicLike.setText(pic.getPicLike() + " like");
        //
        Glide.with(getApplicationContext()).load(myAvatar).into(imvAvatar);
    }

    @Override
    public void onUpdatePicDetailSuccess() {

    }

    @Override
    public void onFail(String msg) {

    }

    @OnClick({R.id.imv_pic, R.id.imv_cmt_click, R.id.imb_send_new_cmt})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_pic:
                break;
            case R.id.imv_cmt_click:
                break;
            case R.id.imb_send_new_cmt:
                break;
        }
    }
}
