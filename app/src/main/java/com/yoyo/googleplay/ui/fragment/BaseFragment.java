package com.yoyo.googleplay.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.yoyo.googleplay.ui.widget.LoadingPage;
import com.yoyo.googleplay.utils.UIUtils;

import java.util.ArrayList;
import java.util.Objects;

/**
 * A simple {@link Fragment} subclass.
 */
public abstract class BaseFragment extends Fragment {


    private LoadingPage mLoadingPage;

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLoadingPage = new LoadingPage(UIUtils.getContext()){

            @Override
            public View onCreateSuccessView() {
                return BaseFragment.this.onCreateSuccessView();
            }

            @Override
            public ResultState onLoad() {
                return BaseFragment.this.onLoad();
            }
        };
        return mLoadingPage;
    }

    public abstract View onCreateSuccessView();
    public abstract LoadingPage.ResultState onLoad();
    public void loadData(){
        if(mLoadingPage != null){
            mLoadingPage.loadData();
        }
    }

    public LoadingPage.ResultState checkData(Object obj){
        if (obj != null){
            if (obj instanceof ArrayList){
                ArrayList list = (ArrayList) obj;
                if (!list.isEmpty()){
                    return LoadingPage.ResultState.LOAD_SUCCESS;
                }else{
                    return LoadingPage.ResultState.LOAD_EMPTY;
                }
            }
        }

        return LoadingPage.ResultState.LOAD_ERROR;
    }
}
