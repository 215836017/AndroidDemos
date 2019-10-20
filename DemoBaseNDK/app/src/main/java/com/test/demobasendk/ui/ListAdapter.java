package com.test.demobasendk.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.test.demobasendk.R;

public class ListAdapter extends BaseAdapter {

    private LayoutInflater inflater;
    private String[] items;

    public ListAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.items = context.getResources().getStringArray(R.array.operaions);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public String getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (null == convertView) {
            viewHolder = new ViewHolder();
            convertView = inflater.inflate(R.layout.layout_list_item, parent, false);

            viewHolder.textView = convertView.findViewById(R.id.item_text);

            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.textView.setText(items[position]);

        return convertView;
    }

    class ViewHolder {
        TextView textView;
    }
}
