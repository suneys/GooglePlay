package com.yoyo.googleplay.ui.fragment;

import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yoyo.googleplay.R;
import com.yoyo.googleplay.ui.adapter.MyBaseAdapter;
import com.yoyo.googleplay.ui.holder.BaseHolder;
import com.yoyo.googleplay.ui.holder.HomeHolder;
import com.yoyo.googleplay.ui.widget.LoadingPage;
import com.yoyo.googleplay.utils.UIUtils;

import java.util.ArrayList;

/**
 * 首页
 * Created by Administrator on 2016/5/16 0016.
 */
public class HomeFragment extends BaseFragment {

    ArrayList<String> mList = new ArrayList<>();

    @Override
    public View onCreateSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        initData();
        HomeAdapter homeAdapter = new HomeAdapter(mList);
        listView.setAdapter(homeAdapter);
        return listView;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        return LoadingPage.ResultState.LOAD_SUCCESS;
    }

    void initData() {
        for (int i = 0; i < 20; i++) {
            mList.add("测试数据" + i);
        }
    }

    class HomeAdapter extends MyBaseAdapter<String> {

        public HomeAdapter(ArrayList<String> list) {
            super(list);
        }

        @Override
        public BaseHolder<String> getHolder(int position) {
            return new HomeHolder();
        }
    }

}

