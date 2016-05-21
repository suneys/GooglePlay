package com.yoyo.googleplay.ui.holder;

import android.view.View;
import android.widget.TextView;

import com.yoyo.googleplay.R;
import com.yoyo.googleplay.utils.UIUtils;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class HomeHolder extends BaseHolder<String> {
    private TextView tvContent;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_home);
        tvContent = (TextView) view.findViewById(R.id.tv_content);
        return view;
    }

    @Override
    public void refreshView(String data) {
        tvContent.setText(data);
    }
}
