package ominext.android.vn.nicepic.ui.profile;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;
import ominext.android.vn.nicepic.MyApplication;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.di.module.ViewModule;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
import ominext.android.vn.nicepic.ui.Base.EventClick;
import ominext.android.vn.nicepic.ui.login.LoginActivity;
import ominext.android.vn.nicepic.ui.main.HomeActivity;
import ominext.android.vn.nicepic.utils.Constants;

import static ominext.android.vn.nicepic.utils.Constants.REQUEST_CHOOSE_PHOTO;
import static ominext.android.vn.nicepic.utils.Constants.REQUEST_TAKE_PHOTO;

public class ProfileActivity extends BaseActivity implements ProfileView,EventClick {

    @BindView(R.id.imv_avatar)
    CircleImageView imvAvatar;
    @BindView(R.id.progress_avatar)
    ProgressBar progressAvatar;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_email_user)
    TextView tvEmailUser;
    @BindView(R.id.tv_number)
    TextView tvNumber;
    @BindView(R.id.tv_dang_xuat)
    TextView tvDangXuat;
    @BindView(R.id.civ_back_home)
    CircleImageView civBackHome;
    @BindView(R.id.me_toolbar)
    Toolbar meToolbar;
    @Inject
    ProfilePresenter profilePresenter;
    @Inject
    SharedPreferences preferences;


    @Override
    public void initContentview() {
        setContentView(R.layout.activity_profile);

    }

    @Override
    public void injectDependence() {
        MyApplication.get().getComponent()
                .plus(new ViewModule(this))
                .injectTo(this);
    }

    @Override
    public void binView() {
        ButterKnife.bind(this);
        progressAvatar.setVisibility(View.GONE);
    }
    @Override
    public void initData() {
        String userAvatar = preferences.getString(Constants.PREF_USER_AVATAR, "");
        String userName = preferences.getString(Constants.PREF_USER_NAME,"");
        String userEmail = preferences.getString(Constants.PREF_USER_EMAIL,"");
        Glide.with(this).load(userAvatar).into(imvAvatar);
        tvName.setText(userName);
        tvEmailUser.setText("Email: " + userEmail);
    }

    @Override
    public void loadDataSuccess() {

    }

    @Override
    public void onLogOutSuccess() {
        onStartActivity(LoginActivity.class);
    }

    @Override
    public void onUpLoadAvatarFail(String msg) {

    }

    @Override
    public void onUploadAvatarSuccess(String downloadUrl) {
        progressAvatar.setVisibility(View.GONE);
        Glide.with(getApplicationContext()).load(downloadUrl).into(imvAvatar);
    }


    @OnClick({R.id.imv_avatar, R.id.tv_dang_xuat, R.id.civ_back_home})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_avatar:
                updateAvatar();
                break;
            case R.id.tv_dang_xuat:
                profilePresenter.onLogOut();
                break;
            case R.id.civ_back_home:
                onStartActivity(HomeActivity.class);
                break;
        }
    }

    private void updateAvatar() {
        String item1 = "chọn hình";
        String item2 = "chụp hình";
        onShowBuiderEvent(item1, item2,this);
    }


    @Override
    public void eventChoosePhoto() {
        startActivityForResult(new Intent(Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                , REQUEST_CHOOSE_PHOTO);

    }



    @Override
    public void eventTakePicture() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap = null;
        progressAvatar.setVisibility(View.VISIBLE);
        if (resultCode == RESULT_OK && requestCode == REQUEST_TAKE_PHOTO) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
        } else if (resultCode == RESULT_OK && requestCode == REQUEST_CHOOSE_PHOTO && data != null) {
            Uri uri = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(
                        this.getContentResolver(), uri);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if(bitmap!=null){
            ByteArrayOutputStream imageByte = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 70, imageByte);
            byte[] b = imageByte.toByteArray();
            profilePresenter.uploadUserAvatar(b);
        }



    }
}
