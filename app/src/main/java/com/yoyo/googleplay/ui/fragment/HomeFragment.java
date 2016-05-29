package com.yoyo.googleplay.ui.fragment;

import android.graphics.Color;
import android.os.SystemClock;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yoyo.googleplay.R;
import com.yoyo.googleplay.domain.AppInfo;
import com.yoyo.googleplay.http.protocol.HomeProtocol;
import com.yoyo.googleplay.ui.adapter.MyBaseAdapter;
import com.yoyo.googleplay.ui.holder.BaseHolder;
import com.yoyo.googleplay.ui.holder.HomeHolder;
import com.yoyo.googleplay.ui.widget.LoadingPage;
import com.yoyo.googleplay.utils.UIUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 首页
 * Created by Administrator on 2016/5/16 0016.
 */
public class HomeFragment extends BaseFragment {

    ArrayList<AppInfo> mAppData = new ArrayList<>();
    private HomeProtocol homeProtocol;

    @Override
    public View onCreateSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        HomeAdapter homeAdapter = new HomeAdapter(mAppData);
        listView.setAdapter(homeAdapter);
        return listView;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        homeProtocol = new HomeProtocol();
        mAppData = homeProtocol.getData(0);

        return checkData(mAppData);
    }


    class HomeAdapter extends MyBaseAdapter<AppInfo> {

        public HomeAdapter(ArrayList<AppInfo> list) {
            super(list);
        }

        @Override
        public List<AppInfo> onLoadMoreData() {
            List<AppInfo> moreData;
            moreData = homeProtocol.getData(mAppData.size());
            return moreData;
        }

        @Override
        public BaseHolder<AppInfo> getHolder(int position) {
            return new HomeHolder();
        }


    }

}

