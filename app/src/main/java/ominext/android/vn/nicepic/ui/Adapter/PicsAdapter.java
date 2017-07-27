package ominext.android.vn.nicepic.ui.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.data.model.Pic;

/**
 * Created by MyPC on 27/07/2017.
 */

public class PicsAdapter extends RecyclerView.Adapter<PicsAdapter.PicViewHolder> {
    View v;
    private ArrayList<Pic> pics;

    public PicsAdapter(ArrayList<Pic> pics) {
        this.pics = pics;
    }


    @Override
    public PicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_item_pic_small, parent, false);

        return new PicViewHolder(v);
    }


    @Override
    public void onBindViewHolder(PicViewHolder holder, int position) {
        Pic pic = pics.get(position);
        holder.tvPicCmt.setText(pic.getPicCmt() + " comment");
        holder.tvPicLike.setText(pic.getPicLike() + " like");
        Glide.with(v.getContext()).load(pic.getPicUrl())
                .into(holder.imvPic);
        String picURL = pic.getPicUrl();
        boolean isGif = picURL.charAt(picURL.length() - 1) == 'f' ? true : false;
        if (isGif) {
            Glide.with(v.getContext())
                    .load(pic.getPicUrl())
                    .asGif()
                    .into(holder.imvPic);
        } else {
            Glide.with(v.getContext())
                    .load(pic.getPicUrl())
                    .into(holder.imvPic);

        }
    }

    @Override
    public int getItemCount() {
        return pics.size();
    }

    @OnClick(R.id.imv_pic)
    public void onViewClicked() {
    }

    public class PicViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_pic)
        ImageView imvPic;
        @BindView(R.id.tv_pic_like)
        TextView tvPicLike;
        @BindView(R.id.tv_pic_cmt)
        TextView tvPicCmt;

        public PicViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
