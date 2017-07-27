package ominext.android.vn.nicepic.ui.Base;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;

import ominext.android.vn.nicepic.R;


public abstract class BaseActivity extends AppCompatActivity {
    private ProgressDialog dialog;
    private AlertDialog.Builder builder;

    public abstract void initContentview();

    public abstract void injectDependence();

    public abstract void binView();

    public abstract void initData();



    @Override
    public void onBackPressed() {

        finish();
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initContentview();
        binView();
        injectDependence();
        initData();
//        replaceFragment(initFragment());
    }

//
//    private void replaceFragment(BaseFragment baseFragment) {
//        getSupportFragmentManager()
//                .beginTransaction()
//                .replace(R.id.container, baseFragment)
//                .commit();
//    }


    public void showLoading() {
        if (dialog != null) {
            if (dialog.isShowing()) dialog.dismiss();
            dialog.show();
            return;
        }
        dialog = ProgressDialog
                .show(this, "", "Loading. Please wait...", true);
    }

    public void hideLoading() {
        if (dialog != null && dialog.isShowing())
            dialog.dismiss();
    }

    public void onStartActivity(Class aClass) {
        startActivity(new Intent(this, aClass));
    }

    public void onShowBuider(String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Thông Báo");
        builder.setMessage(msg);
        builder.setIcon(R.drawable.logo);
        builder.setCancelable(true);
        final AlertDialog  dialog =builder.create();
        builder.setNegativeButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public void onShowBuiderEvent(final String item1, final String item2, final EventClick eventClick) {
        final CharSequence[] items = {item1, item2};
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(getResources().getString(R.string.titleavatar));
        builder.setIcon(R.drawable.ic_avatar);
        builder.setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (items[i].equals(item1)) {
                    eventClick.eventChoosePhoto();
                } else if (items[i].equals(item2)) {
                    eventClick.eventTakePicture();
                }
            }
        });
        builder.setNegativeButton("cancle", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                builder.setCancelable(true);
            }
        });
        builder.create().show();
    }




}