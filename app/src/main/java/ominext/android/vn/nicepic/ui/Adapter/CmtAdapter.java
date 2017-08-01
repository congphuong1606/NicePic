package ominext.android.vn.nicepic.ui.Adapter;

import android.content.Context;
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
import de.hdodenhof.circleimageview.CircleImageView;
import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.data.model.Comment;
import ominext.android.vn.nicepic.data.model.Pic;

/**
 * Created by MyPC on 28/07/2017.
 */

public class CmtAdapter extends RecyclerView.Adapter<CmtAdapter.CmtViewHolder> {


    enum ItemType {
        FIST_PAGE, CMT_PAGE;
    }

    private Pic pic;
    View v;
    Context context;
    ArrayList<Comment> comments;

    public CmtAdapter(ArrayList<Comment> comments, Pic pic) {
        this.comments = comments;
        this.pic = pic;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return ItemType.FIST_PAGE.ordinal();
        }
        return ItemType.CMT_PAGE.ordinal();
    }

    @Override
    public CmtViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        context = v.getContext();
        if (viewType == ItemType.FIST_PAGE.ordinal()) {
            v = LayoutInflater.from(viewGroup.
                    getContext()).inflate(R.layout.layout_first_page, viewGroup, false);
        } else {
            v = LayoutInflater.from(viewGroup.
                    getContext()).inflate(R.layout.layout_item_cmt, viewGroup, false);
        }
        return new CmtViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CmtViewHolder holder, int position) {
        if (position == 0) {
            holder.tvPicName.setText(pic.getPicName());
            holder.tvPicDescription.setText(pic.getPicDescription());
            holder.tvPicLike.setText(pic.getPicLike());
            holder.tvPicComment.setText(pic.getPicCmt());

        } else {
            Comment comment = comments.get(position - 1);
            Glide.with(context).load(comment.getCmtAvatarUser()).into(holder.imvCmtUser);
            holder.tvCmtContent.setText(comment.getCmtContent());
            if (comment.getCmtLike() != 0) {
                holder.tvCmtLike.setText(comment.getCmtLike());
            }
            holder.tvCmtTime.setText(comment.getCmtTime());
        }

    }

    @Override
    public int getItemCount() {
        return comments.size()+1;
    }

    public class CmtViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.imv_cmt_user)
        CircleImageView imvCmtUser;
        @BindView(R.id.tv_cmt_content)
        TextView tvCmtContent;
        @BindView(R.id.tv_cmt_time)
        TextView tvCmtTime;
        @BindView(R.id.tv_cmt_like)
        TextView tvCmtLike;
        //
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

        public CmtViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
