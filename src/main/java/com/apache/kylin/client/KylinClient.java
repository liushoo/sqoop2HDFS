package org.apache.hadoop.kylin.client;

import com.alibaba.fastjson.JSON;
import org.apache.hadoop.kylin.entity.CubeConfig;
import org.apache.hadoop.kylin.entity.KylinConfig;
import org.apache.hadoop.kylin.entity.response.BuildResponse;

import org.apache.hadoop.kylin.entity.response.KylinJob;

import org.apache.hadoop.retrofit.RetrofitClient;
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
