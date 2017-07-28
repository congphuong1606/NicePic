package ominext.android.vn.nicepic.ui.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import ominext.android.vn.nicepic.R;
import ominext.android.vn.nicepic.data.model.Comment;

/**
 * Created by MyPC on 28/07/2017.
 */

public class CmtAdapter extends RecyclerView.Adapter<CmtAdapter.CmtViewHolder>{
    View v;
    Context context;
    ArrayList<Comment> comments;

    public CmtAdapter(ArrayList<Comment> comments) {
        this.comments = comments;
    }

    @Override
    public CmtViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.layout_item_cmt, viewGroup, false);
        context=v.getContext();
        return new CmtViewHolder(v);
    }

    @Override
    public void onBindViewHolder(CmtViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CmtViewHolder extends RecyclerView.ViewHolder{

        public CmtViewHolder(View itemView) {
            super(itemView);
        }
    }
}
