package com.yoyo.googleplay.ui.fragment;

import android.view.View;

import com.yoyo.googleplay.ui.widget.LoadingPage;

/**
 * 游戏
 * Created by Administrator on 2016/5/16 0016.
 */
public class GameFragment extends BaseFragment {
    @Override
    public View onCreateSuccessView() {
        return null;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.LOAD_ERROR;
    }
}
