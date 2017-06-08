package com.ilottery.kylin.client;

import com.alibaba.fastjson.JSON;
import com.ilottery.kylin.entity.CubeConfig;
import com.ilottery.kylin.entity.KylinConfig;
import com.ilottery.kylin.entity.response.BuildResponse;

import com.ilottery.kylin.entity.response.KylinJob;

import com.ilotterytech.retrofit.RetrofitClient;
import com.ilotterytech.retrofit.fastjson.converter.FastJsonConverterFactory;
import com.squareup.okhttp.MediaType;
import com.squareup.okhttp.RequestBody;
import retrofit.Call;
import retrofit.Response;
import retrofit.Retrofit;

import java.io.IOException;

/**
 * Created by hxd on 2017/6/6.
 */
public class KylinClient extends RetrofitClient{

    private KylinConfig kylinConfig = null;

    private static KylinService service = null;

    /**
     * 初始化
     */
    public KylinClient(KylinConfig kylinConfig) {
        super(kylinConfig.getUrl());
        this.kylinConfig=kylinConfig;
    }

    public BuildResponse buildCube(CubeConfig cubeConfig) {
        Call<BuildResponse> call = service.buildCube(kylinConfig.getAuthorization(),kylinConfig.getCubeName(), RequestBody.create(jsonReq, JSON.toJSONString(cubeConfig)));
        return executeCall(call);
    }


    public KylinJob jobResume(KylinJob job) {
        //kylinConfig.getAuthorization(),
        Call<KylinJob> call = service.jobResume(kylinConfig.getAuthorization(),job.getUuid());
        return executeCall(call);
    }

}
