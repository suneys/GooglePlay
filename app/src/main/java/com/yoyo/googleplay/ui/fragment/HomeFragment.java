package com.yoyo.googleplay.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.widget.TextView;

import com.yoyo.googleplay.ui.widget.LoadingPage;
import com.yoyo.googleplay.utils.UIUtils;

/**
 * 首页
 * Created by Administrator on 2016/5/16 0016.
 */
public class HomeFragment extends BaseFragment {
    @Override
    public View onCreateSuccessView() {
        TextView txt = new TextView(UIUtils.getContext());
        txt.setText("HomeFragment");
        txt.setTextColor(Color.BLACK);
        return txt;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.LOAD_SUCCESS;
    }
}
