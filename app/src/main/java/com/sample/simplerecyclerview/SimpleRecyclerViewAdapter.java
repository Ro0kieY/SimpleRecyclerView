package com.sample.simplerecyclerview;

import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

/**
 * Created by Jia on 2017/6/21.
 */

public class SimpleRecyclerViewAdapter extends RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder> implements ItemTouchHelperAdapter{

    private List<Bean> list;

    public SimpleRecyclerViewAdapter(List<Bean> list) {
        this.list = list;
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(list, fromPosition, toPosition);
        notifyItemMoved(fromPosition, toPosition);
        notifyItemRangeChanged(fromPosition, fromPosition +1);
        notifyItemRangeChanged(toPosition, toPosition +1);
        Log.d("FunnyRVAdapter", "onItemMove: onItemMove");
    }

    @Override
    public void onItemSwiped(int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_username, tv_time, tv_message;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_username = (TextView) itemView.findViewById(R.id.tv_username);
            tv_time = (TextView) itemView.findViewById(R.id.tv_time);
            tv_message = (TextView) itemView.findViewById(R.id.tv_message);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            view.setBackgroundColor(0X55AAFF99);
        }
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Bean bean = list.get(position);
        holder.tv_username.setText(bean.getUsername());
        holder.tv_time.setText(bean.getTime());
        holder.tv_message.setText(bean.getMessage());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

}
