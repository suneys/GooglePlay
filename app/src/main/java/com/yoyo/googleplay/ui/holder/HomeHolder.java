package com.yoyo.googleplay.ui.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.yoyo.googleplay.R;
import com.yoyo.googleplay.domain.AppInfo;
import com.yoyo.googleplay.utils.UIUtils;

import android.text.format.Formatter;

/**
 * Created by Administrator on 2016/5/21 0021.
 */
public class HomeHolder extends BaseHolder<AppInfo> {

    private ImageView ivIcon;
    private TextView tvName;
    private RatingBar rbStart;
    private TextView tvSize;
    private TextView tvDesc;

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_home);
        ivIcon = (ImageView) view.findViewById(R.id.iv_icon);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        rbStart = (RatingBar) view.findViewById(R.id.rb_start);
        tvSize = (TextView) view.findViewById(R.id.tv_size);
        tvDesc = (TextView) view.findViewById(R.id.tv_desc);
        return view;
    }



    @Override
    public void refreshView(AppInfo data) {
        tvName.setText(data.name);
        rbStart.setRating(data.stars);
        tvSize.setText(Formatter.formatFileSize(UIUtils.getContext(),data.size));
        tvDesc.setText(data.des);
    }
}
