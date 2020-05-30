package com.cakes.demogpuimage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class FragFilterList extends Fragment {

    private final String TAG = "FragFilterList";
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.layout_filter_list, container, false);
        listView = view.findViewById(R.id.filter_list);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),
                android.R.layout.simple_list_item_1,
                FilterList.getList());
        LogUtil.i(TAG, "onCreateView() --- 11111111111, list.size = " + FilterList.getList().size());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (null != listenerInFragment) {
                    listenerInFragment.onItemClicked(position, FilterList.getList().get(position));
                }
            }
        });
        return view;
    }


    private OnListenerInFragment listenerInFragment;

    public void setListenerInFragment(OnListenerInFragment onListenerInFragment) {
        this.listenerInFragment = onListenerInFragment;
    }

    public interface OnListenerInFragment {
        void onItemClicked(int pos, String value);
    }
}
