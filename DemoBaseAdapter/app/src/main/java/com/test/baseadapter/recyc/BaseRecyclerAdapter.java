package com.test.baseadapter.recyc;

import android.content.Context;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 相关链接：https://www.cnblogs.com/huangjialin/p/7808599.html
 */
public abstract class BaseRecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {

    private Context context;
    private int layoutId;
    private List<T> datas;

    public BaseRecyclerAdapter(Context context, int layoutId, List<T> datas) {
        this.context = context;
        this.layoutId = layoutId;
        this.datas = datas;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BaseViewHolder.getBaseViewHolder(context, parent, layoutId);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        conver(holder, datas.get(position));
    }

    @Override
    public int getItemCount() {
        return datas.size();
    }

    public abstract void conver(BaseViewHolder holder, T t);


    public interface ConmonItemType<T> {
        int getLayoutId(int itemType); //不同的Item布局

        int getItemViewType(int position, T t);
    }
}
