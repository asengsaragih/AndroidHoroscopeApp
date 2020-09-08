package com.suncode.horoscope.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.suncode.horoscope.R;
import com.suncode.horoscope.model.HoroscopeCollection;

import java.util.List;

public class HoroscopeListAdapter extends RecyclerView.Adapter<HoroscopeListAdapter.ListHolder> {

    private Context mContext;
    private List<HoroscopeCollection> mData;
    private ClickHandler mClickHandler;

    public HoroscopeListAdapter(Context mContext, List<HoroscopeCollection> mData, ClickHandler mClickHandler) {
        this.mContext = mContext;
        this.mData = mData;
        this.mClickHandler = mClickHandler;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.list_item_horoscope, parent, false);
        return new ListHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        Drawable icon = mData.get(position).getIcon();
        String title = mData.get(position).getTitle();
        String interval = mData.get(position).getInterval();

        holder.titleTextview.setText(title);
        holder.intervalTextview.setText(interval);

        Glide.with(mContext)
                .load(icon)
                .into(holder.iconImageview);

        holder.baseConstraint.setOnClickListener(v -> {
            mClickHandler.onClickListener(title);
        });
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    static class ListHolder extends RecyclerView.ViewHolder {

        final ImageView iconImageview;
        final TextView titleTextview;
        final TextView intervalTextview;
        final View baseConstraint;

        public ListHolder(@NonNull View itemView) {
            super(itemView);

            iconImageview = itemView.findViewById(R.id.imageview_list_item_icon);
            titleTextview = itemView.findViewById(R.id.textView_list_item_name);
            intervalTextview = itemView.findViewById(R.id.textView_list_item_desc);

            baseConstraint = itemView.findViewById(R.id.constraintLayout_list_item_canvas);
        }
    }


    public interface ClickHandler {
        void onClickListener(String name);
    }
}
