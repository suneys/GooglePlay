package com.yoyo.googleplay.ui.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.yoyo.googleplay.ui.holder.BaseHolder;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public abstract class MyBaseAdapter<T> extends BaseAdapter {

    private ArrayList<T> list;

    public MyBaseAdapter(ArrayList<T> list){
        this.list = list;
    }
    @Override
    public int getCount() {
        return list.size();
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
        BaseHolder<T> holder;
        if(convertView == null){
            holder = getHolder(position);
        }else{
            holder = (BaseHolder<T>) convertView.getTag();
        }
        holder.setData(getItem(position));
        return holder.getRootView();
    }

    public abstract BaseHolder<T> getHolder(int position);
}
