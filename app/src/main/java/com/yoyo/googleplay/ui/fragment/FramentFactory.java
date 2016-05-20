package com.yoyo.googleplay.ui.fragment;

import android.support.v4.app.Fragment;

import java.util.HashMap;

/**
 * Created by Administrator on 2016/5/16 0016.
 */
public class FramentFactory {

    public static HashMap<Integer,BaseFragment> mFragmentMap = new HashMap<Integer,BaseFragment>();

    /**
     * 根据位置生成fragment
     * @param pos
     * @return
     */
    public static BaseFragment createFragment(int pos){
        BaseFragment fragment = mFragmentMap.get(pos);

        if(fragment == null){

            switch (pos) {
                case 0:
                    fragment = new HomeFragment();
                    break;
                case 1:
                    fragment = new AppFragment();
                    break;
                case 2:
                    fragment = new GameFragment();
                    break;
                case 3:
                    fragment = new SubjectFragment();
                    break;
                case 4:
                    fragment = new RecommentFragment();
                    break;
                case 5:
                    fragment = new CategoryFragment();
                    break;
                case 6:
                    fragment = new HotFragment();
                    break;
                default:
                    break;
            }

            //将Fragment添加到集合中，下次就不用再创建新的对象了
            mFragmentMap.put(pos,fragment);
        }

        return fragment;
    }
}
