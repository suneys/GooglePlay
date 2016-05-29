package com.yoyo.googleplay.http.protocol;

import com.yoyo.googleplay.http.HttpHelper;
import com.yoyo.googleplay.utils.IOUtils;
import com.yoyo.googleplay.utils.StringUtils;
import com.yoyo.googleplay.utils.UIUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by Administrator on 2016/5/29 0029.
 */
public abstract class BaseProtocol<T> {

    public T getData(int index) {
        //判断是否有缓存，如果有，直接加载缓存
        String result = getCache(index);
        if(StringUtils.isEmpty(result)){
            //没有缓存，访问网络
            result = getDataFromServer(index);
        }
        //System.out.println("result:" + result);
        return parseJson(result);
    }

    public abstract T parseJson(String result);

    /**
     * 访问网络，获取数据
     *
     * index:表示服务器从哪个位置返回20条数据（用于分页）
     */
    private String getDataFromServer(int index) {
        //http://www.itcast.cn/home?index=0&name=zhangsan&age=18
        //链接=主域名+接口字段 + 参数
       HttpHelper.HttpResult httpResult= HttpHelper.get(HttpHelper.URL + getKey() + "?index=" +
                index + getParam());
        if(httpResult != null){
            String result = httpResult.getString();
            if(!StringUtils.isEmpty(result)) {
                setCache(result, index);
            }
            return result;
        }
        return null;
    }

    /**
     * 返回具体接口字段，必须由子类实现
     *
     * @return
     */
    public abstract String getKey();

    /**
     * 接口参数，必须由子类实现
     *
     * @return
     */
    public abstract String getParam();

    private void setCache(String json,int index){
        FileWriter out = null;
        File cacheDir = UIUtils.getContext().getCacheDir();
        File cacheFile = new File(cacheDir,getKey() + "?index=" +
                index + getParam());
        try {
            out =   new FileWriter(cacheFile);
            out.write(System.currentTimeMillis()+ 30 * 60 * 1000 + "\r");
            out.write(json);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            IOUtils.close(out);
        }
    }

    private String getCache(int index){
        File cacheDir = UIUtils.getContext().getCacheDir();
        File cacheFile = new File(cacheDir,getKey() + "?index=" +
                index + getParam());
        BufferedReader reader = null;
        if(cacheFile.exists()) {
            //有缓存
            try {
                reader = new BufferedReader(new FileReader(cacheFile));
                long timeOver = Long.parseLong(reader.readLine());
                if (timeOver > System.currentTimeMillis()){
                    //缓存未过期
                    String line = null;
                    StringBuffer sb = new StringBuffer();
                    while((line = reader.readLine()) != null){
                        sb.append(line);
                    }
                    return sb.toString();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally {
                IOUtils.close(reader);
            }
        }
        return null;
    }
}
