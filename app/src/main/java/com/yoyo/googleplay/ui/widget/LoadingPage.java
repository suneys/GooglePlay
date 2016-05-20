package com.yoyo.googleplay.ui.widget;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;

import com.yoyo.googleplay.R;
import com.yoyo.googleplay.utils.UIUtils;

/**
 * Created by Administrator on 2016/5/18 0018.
 */
public abstract class LoadingPage extends FrameLayout {

    private static final int STATE_LOAD_UNDO = 0; //未加载
    private static final int STATE_LOAD_LOADING = 1; //正在加载
    private static final int STATE_LOAD_EMPTY = 2;  // 数据为空
    private static final int STATE_LOAD_ERROR = 3;  // 加载失败
    private static final int STATE_LOAD_SUCCESS = 4; //加载成功

    private int mCrrentState = STATE_LOAD_UNDO; //当前状态，默认为未加载

    private View mLoadingView;
    private View mEmptyView;
    private View mErrorView;
    private View mSuccessView;

    public LoadingPage(Context context) {
        super(context);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        //加载中的布局
        if (mLoadingView == null) {
            mLoadingView = onCreateLoadingView();
            addView(mLoadingView);
        }

        //数据为空的布局
        if (mEmptyView == null) {
            mEmptyView = onCreateEmptyView();
            addView(mEmptyView);
        }

        //数据加载失败的布局
        if (mErrorView == null) {
            mErrorView = onCreateErrorView();
            addView(mErrorView);
        }

        showRightPage();
    }

    private void showRightPage() {

        if (mLoadingView != null) {
            mLoadingView.setVisibility((mCrrentState == STATE_LOAD_UNDO || mCrrentState ==
                    STATE_LOAD_LOADING) ? VISIBLE : GONE);
        }

        if (mErrorView != null) {
            mErrorView.setVisibility(mCrrentState == STATE_LOAD_ERROR ? VISIBLE : GONE);
        }

        if (mEmptyView != null) {
            mEmptyView.setVisibility(mCrrentState == STATE_LOAD_EMPTY ? VISIBLE : GONE);
        }

        //如果成功布局为空并且当前状态为加载成功，才去初始化布局
        if (mSuccessView == null && mCrrentState == STATE_LOAD_SUCCESS) {
            mSuccessView = onCreateSuccessView();
            if (mSuccessView != null) {
                addView(mSuccessView);
            }
        }

        if (mSuccessView != null) {
            mSuccessView.setVisibility(mCrrentState == STATE_LOAD_SUCCESS ? VISIBLE : GONE);
        }
    }

    /**
     * 加载数据布局对象
     *
     * @return
     */
    public View onCreateLoadingView() {
        return UIUtils.inflate(R.layout.layout_loading);
    }

    /**
     * 加载数据为空布局对象
     *
     * @return
     */
    public View onCreateEmptyView() {
        return UIUtils.inflate(R.layout.layout_empty);
    }

    /**
     * 加载数据为空布局对象
     *
     * @return
     */
    public View onCreateErrorView() {
        return UIUtils.inflate(R.layout.layout_error);
    }

    /**
     * 加载数据成功对象
     *
     * @return
     */
    public abstract View onCreateSuccessView();

    /**
     * 加载网络数据
     */
    public void loadData() {
        new Thread() {
            @Override
            public void run() {
                final ResultState result = onLoad();
                UIUtils.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        if(result != null) {
                            mCrrentState = result.getSate();
                            showRightPage();
                        }
                    }
                });
            }
        }.start();
    }

    public abstract ResultState onLoad();

    public enum ResultState {
        LOAD_SUCCESS(STATE_LOAD_SUCCESS), LOAD_EMPTY(STATE_LOAD_EMPTY), LOAD_ERROR
                (STATE_LOAD_ERROR);

        private int state;

        ResultState(int state) {
            this.state = state;
        }

        public int getSate() {
            return state;
        }

    }
}
