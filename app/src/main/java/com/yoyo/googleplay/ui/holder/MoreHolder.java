package com.yoyo.googleplay.ui.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yoyo.googleplay.R;
import com.yoyo.googleplay.utils.UIUtils;

/**
 * Created by Administrator on 2016-05-23.
 */
public class MoreHolder extends BaseHolder<Integer> {

    public static final int TYPE_HAS_MORE = 0;
    public static final int TYPE_NO_MORE = 1;
    public static final int TYPE_MORE_ERROR = 2;
    private LinearLayout llHasMore;
    private TextView tvMoreError;

    public MoreHolder(Boolean hasMore) {
        setData(hasMore ? TYPE_HAS_MORE : TYPE_NO_MORE);
    }

    @Override
    public View initView() {
        View view = UIUtils.inflate(R.layout.list_item_more);
        llHasMore = (LinearLayout) view.findViewById(R.id.ll_has_more);
        tvMoreError = (TextView) view.findViewById(R.id.tv_more_error);
        return view;
    }


    @Override
    public void refreshView(Integer data) {
        switch (data) {
            case TYPE_HAS_MORE:
                llHasMore.setVisibility(View.VISIBLE);
                tvMoreError.setVisibility(View.GONE);
                break;
            case TYPE_NO_MORE:
                llHasMore.setVisibility(View.GONE);
                tvMoreError.setVisibility(View.GONE);
                break;
            case TYPE_MORE_ERROR:
                llHasMore.setVisibility(View.GONE);
                tvMoreError.setVisibility(View.VISIBLE);
                break;


        }
    }
}
