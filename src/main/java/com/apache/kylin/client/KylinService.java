package org.apache.hadoop.kylin.client;

import com.ilottery.kylin.entity.response.BuildResponse;
import com.ilottery.kylin.entity.response.KylinJob;
import com.squareup.okhttp.RequestBody;
import retrofit.Call;
import retrofit.http.*;

/**
 * Created by liush on 2017/6/6.
 */
public interface KylinService {

    @PUT("api/cubes/{cubeName}/build")
    Call<BuildResponse> buildCube(@Header("Authorization") String authorization, @Path("cubeName") String cubeName, @Body RequestBody requestBody);

    //@PUT("api/jobs/{jobId}/resume")
    //@Header("Authorization") String authorization,
    @GET("api/jobs/{jobId}")
    Call<KylinJob> jobResume( @Header("Authorization") String authorization, @Path("jobId") String jobId);

}
