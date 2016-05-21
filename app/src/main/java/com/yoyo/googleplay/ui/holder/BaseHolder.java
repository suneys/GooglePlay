package com.yoyo.googleplay.ui.holder;

import android.view.View;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public abstract class BaseHolder<T> {

    private View rootView;
    private T data;
    public BaseHolder(){
        rootView = initView();
        rootView.setTag(this);
    }

    public abstract View initView();

    public void setData(T data){
        this.data = data;
        refreshView(data);
    }

    public View getRootView() {
        return rootView;
    }

    public T getData() {
        return data;
    }

    public abstract void refreshView(T data);
}
