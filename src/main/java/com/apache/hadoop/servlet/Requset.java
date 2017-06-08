package com.hadoop.hadoop.servlet;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.entity.Application;
import org.apache.hadoop.entity.ResoonseJob;
import org.apache.hadoop.kylin.entity.CubeConfig;
import org.apache.hadoop.kylin.entity.KylinConfig;
import org.apache.hadoop.kylin.entity.response.BuildResponse;
import org.apache.hadoop.kylin.entity.response.KylinJob;
import org.apache.hadoop.retrofit.fastjson.converter.FastJsonConverterFactory;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

import java.io.IOException;

/**
 * Created by liush on 2017/6/6.
 */
public class Requset {



    private static Retrofit retrofit = null;

    private static Service service = null;

    private final MediaType jsonReq = MediaType.parse("application/json; charset=utf-8");

    /**
     * 初始化
     */
    public Requset(String url) {

        if(url ==null || url.isEmpty()){
            System.out.println("URL is not null");
            return;
        }
        //初始化Retrofit
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(url)  //定义基本url
                    .addConverterFactory(FastJsonConverterFactory.create())  //定义结果解析器
                    .build();
            service = retrofit.create(Service.class);  //获取Service
        }
    }

    public Application getApplicationInfo(String applicationID) {
        Call<Application> call = service.getApplicationInfo(applicationID);
        try {
            Response<Application> response = call.execute();
            if (response.isSuccess()) {
                return response.body();
            } else {
                throw new RuntimeException("execute error:" + response.code() + ":" + response.message());
            }
        } catch (IOException e) {
            throw new RuntimeException("execute error:" + e.getMessage());
        }
    }


}
