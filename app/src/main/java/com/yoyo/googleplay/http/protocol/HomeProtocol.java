package com.yoyo.googleplay.http.protocol;

import com.yoyo.googleplay.domain.AppInfo;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/5/29 0029.
 */
public class HomeProtocol extends BaseProtocol<ArrayList<AppInfo>> {

    private ArrayList<String> mPictures; //轮播图片


    @Override
    public String getKey() {
        return "home";
    }

    @Override
    public String getParam() {
        return "";
    }

    @Override
    public ArrayList<AppInfo> parseJson(String result) {
        //解析json方式：遇到{}就用JsonObject，遇到[]就用JsonArray
        if (result == null){
            return null;
        }
        try {
            JSONObject jo = new JSONObject(result);
            mPictures = new ArrayList<String>();
            JSONArray ja = jo.getJSONArray("picture");
            for (int i = 0; i < ja.length(); i++){
                String str = ja.getString(i);
                mPictures.add(str);
            }
            ArrayList<AppInfo> mAppInfos = new ArrayList<>();
            JSONArray ja1 = jo.getJSONArray("list");
            for (int i = 0; i < ja1.length(); i++){
                JSONObject jo1 = ja1.getJSONObject(i);
                AppInfo appInfo = new AppInfo();
                appInfo.id = jo1.getString("id");
                appInfo.name = jo1.getString("name");
                appInfo.packageName = jo1.getString("packageName");
                appInfo.iconUrl = jo1.getString("iconUrl");
                appInfo.stars = (float) jo1.getDouble("stars");
                appInfo.size = jo1.getLong("size");
                appInfo.downloadUrl = jo1.getString("downloadUrl");
                appInfo.des = jo1.getString("des");
                mAppInfos.add(appInfo);
            }
            return mAppInfos;
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }
}
