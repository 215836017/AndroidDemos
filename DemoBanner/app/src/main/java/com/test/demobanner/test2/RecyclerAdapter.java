package com.test.demobanner.test2;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.test.demobanner.R;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private LayoutInflater inflater;
    private List<Item> datas;
    private int width;
    private int height;

    public RecyclerAdapter(Context context, List<Item> datas, int width, int height) {
        inflater = LayoutInflater.from(context);
        this.datas = datas;
        this.width = width;
        this.height = height;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = inflater.inflate(R.layout.layout_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(width, height);
        viewHolder.imageView.setLayoutParams(layoutParams);
        viewHolder.imageView.setImageResource(datas.get(i).getImageId());
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.imageView = itemView.findViewById(R.id.recycItem_imageView);
        }
    }
}
