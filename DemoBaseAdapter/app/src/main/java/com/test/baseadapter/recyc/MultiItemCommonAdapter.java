package com.test.baseadapter.recyc;

import android.content.Context;
import android.view.ViewGroup;

import java.util.List;

public abstract class MultiItemCommonAdapter<T> extends BaseRecyclerAdapter {

    private ConmonItemType<T> conmonItemType;
    private List<T> datas;
    private Context context;

    public MultiItemCommonAdapter(Context context, List datas, ConmonItemType<T> conmonItemType) {
        super(context, -1, datas);
        this.conmonItemType = conmonItemType;
        this.datas = datas;
        this.context = context;
    }

    @Override
    public int getItemViewType(int position) {
        return conmonItemType.getItemViewType(position, datas.get(position));
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        int layoutId = conmonItemType.getLayoutId(viewType);
        return BaseViewHolder.getBaseViewHolder(context, parent, layoutId);
    }
}
