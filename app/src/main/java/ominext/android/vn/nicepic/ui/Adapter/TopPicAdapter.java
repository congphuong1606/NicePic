package ominext.android.vn.nicepic.ui.Adapter;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.data.model.Pic;

public class TopPicAdapter extends PagerAdapter {
    private ArrayList<Pic> topPics;
    private LayoutInflater inflater;
    private Context context;

    public TopPicAdapter(Context context, ArrayList<Pic> topPics) {
        this.context = context;
        this.topPics = topPics;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return topPics.size();
    }

    @Override
    public void destroyItem(ViewGroup container,
                            int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        Context context = view.getContext();
        View imageLayout = inflater.inflate(R.layout.layout_top_pic, view, false);

        ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
        int height = context.getResources().getDisplayMetrics().widthPixels;
        layoutParams.height = (2*height) /5;
        view.setLayoutParams(layoutParams);


        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.imv_top_pic);
        String picUrl = topPics.get(position).getPicUrl();
        boolean isGif = picUrl.charAt(picUrl.length() - 1) == 'f' ? true : false;
        if (isGif) {
            Glide.with(context).load(picUrl).asGif()
                    .into(imageView);
        } else {
            Glide.with(context)
                    .load(picUrl)
                    .into(imageView);
        }
        view.addView(imageLayout, 0);
        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }
}