package com.yoyo.googleplay.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yoyo.googleplay.ui.holder.BaseHolder;
import com.yoyo.googleplay.ui.holder.MoreHolder;
import com.yoyo.googleplay.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private static final int TYPE_NORMAL = 0;   //普通模式
    private static final int TYPE_MORE = 1;     //更多模式

    private ArrayList<T> list;
    BaseHolder<T> holder;

    public MyBaseAdapter(ArrayList<T> list){
        this.list = list;
    }

    @Override
    public int getViewTypeCount() {
        return 2;  //普通布局和加载更多布局
    }

    @Override
    public int getItemViewType(int position) {
        if(position == list.size()){
            return TYPE_MORE;
        }else{
            return TYPE_NORMAL;
        }
    }

    @Override
    public int getCount() {
        return list.size() + 1;  //要增加“加载更多”布局
    }

    @Override
    public T getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        if(convertView == null){
            if(getItemViewType(position) == TYPE_NORMAL) {
                holder = getHolder(position);
            }else{
                holder = (BaseHolder<T>) new MoreHolder(hasMore());
            }
        }else{
            holder = (BaseHolder<T>) convertView.getTag();
        }
        if (getItemViewType(position) != TYPE_MORE) {
            holder.setData(getItem(position));
        }else{  //加载更多数据
            MoreHolder moreHolder = (MoreHolder) holder;
            if(moreHolder.getData() == MoreHolder.TYPE_HAS_MORE) {
                loadMoreData(moreHolder);
            }
        }
        return holder.getRootView();
    }

    private boolean isLoadMore = false;
    private void loadMoreData(final MoreHolder holder) {
        if(isLoadMore == false) {  //判断当前是否没有加载更多，避免重复加载数据
            isLoadMore = true;
            new Thread() {
                @Override
                public void run() {
                    final List<T> moreData = onLoadMoreData();
                    UIUtils.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            if (moreData != null) {
                                //每一页20条数据
                                //如果返回的数据少于20，就说明没有更多数据了
                                if (moreData.size() < 20) {
                                    holder.setData(MoreHolder.TYPE_NO_MORE);
                                } else {
                                    holder.setData(MoreHolder.TYPE_HAS_MORE);
                                }

                                list.addAll(moreData);
                                notifyDataSetChanged();
                            } else {
                                //加载失败
                                holder.setData(MoreHolder.TYPE_MORE_ERROR);
                            }
                            isLoadMore = false;
                        }
                    });


                }
            }.start();
        }
    }

    public abstract List<T> onLoadMoreData();


    public Boolean hasMore(){
        return true;
    }

    public abstract BaseHolder<T> getHolder(int position);


}
