package ominext.android.vn.nicepic.ui.newpic;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ominext.android.vn.nicepic.MyApplication;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.di.module.ViewModule;
import ominext.android.vn.nicepic.ui.Base.BaseActivity;
import ominext.android.vn.nicepic.ui.Base.EventClick;
import ominext.android.vn.nicepic.ui.profile.ProfileActivity;

import static ominext.android.vn.nicepic.utils.Constants.REQUEST_CHOOSE_PHOTO;
import static ominext.android.vn.nicepic.utils.Constants.REQUEST_TAKE_PHOTO;

public class NewPicActivity extends BaseActivity implements NewPicView, EventClick {

    @BindView(R.id.imv_upload)
    ImageView imvUpload;
    @BindView(R.id.imb_choose_photo)
    ImageButton imbChoosePhoto;
    @BindView(R.id.imb_take_picture)
    ImageButton imbTakePicture;
    @BindView(R.id.edt_image_description)
    EditText edtTitleUpload;
    @BindView(R.id.imb_send_upload)
    Button imbSendUpload;

    @BindView(R.id.edt_pic_name)
    EditText edtPicName;
    private byte[] iByte;
    @Inject
    NewPicPresenter picPresenter;

    @Override
    public void initContentview() {
        setContentView(R.layout.activity_new_pic);


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

    }


    @OnClick({R.id.imv_upload, R.id.imb_choose_photo, R.id.imb_take_picture, R.id.imb_send_upload})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imv_upload:
                break;
            case R.id.imb_choose_photo:
                eventChoosePhoto();
                break;
            case R.id.imb_take_picture:
                eventTakePicture();
                break;
            case R.id.imb_send_upload:
                upload();


                break;
        }
    }

    private void upload() {
        String picDescription = edtTitleUpload.getText().toString().trim();
        String PicName = edtPicName.getText().toString().trim();
        if (iByte != null) {
            picPresenter.uploadPic(iByte, PicName, picDescription);
        } else {
            onShowBuider("bạn cần chọn một bức hình");
        }

    }

    @Override
    public void upLoadSuccess() {
        onStartActivity(ProfileActivity.class);

    }

    @Override
    public void upLoadFail(String msg) {

    }

    @Override
    public void onUpPicFail(String s) {
        onShowBuider("up ảnh lỗi");
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
        if (bitmap != null) {
            ByteArrayOutputStream imageByte = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, imageByte);
            iByte = imageByte.toByteArray();
        }


    }


}
