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

    ArrayList<String> mList = new ArrayList<>();
    ArrayList<AppInfo> mAppData = new ArrayList<>();

    @Override
    public View onCreateSuccessView() {
        ListView listView = new ListView(UIUtils.getContext());
        initData();
        HomeAdapter homeAdapter = new HomeAdapter(mAppData);
        listView.setAdapter(homeAdapter);
        return listView;
    }

    @Override
    public LoadingPage.ResultState onLoad() {
        HomeProtocol homeProtocol = new HomeProtocol();
        mAppData = homeProtocol.getData(0);

        return checkData(mAppData);
    }

    void initData() {
        for (int i = 0; i < 20; i++) {
            mList.add("测试数据" + i);
        }
    }

    class HomeAdapter extends MyBaseAdapter<AppInfo> {

        public HomeAdapter(ArrayList<AppInfo> list) {
            super(list);
        }

        @Override
        public List<AppInfo> onLoadMoreData() {
            List<String> moreData = new ArrayList<String>();
            for(int i = 0; i < 20; i++){
                moreData.add(i,"更多数据"+i);
            }
            SystemClock.sleep(2000);
            return null;
        }

        @Override
        public BaseHolder<AppInfo> getHolder(int position) {
            return new HomeHolder();
        }


    }

}

