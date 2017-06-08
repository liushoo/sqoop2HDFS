import com.ilottery.hadoop.entity.App;
import com.ilottery.hadoop.servlet.Requset;
import com.ilottery.kylin.client.KylinClient;
import com.ilottery.kylin.entity.BuildType;
import com.ilottery.kylin.entity.CubeConfig;
import com.ilottery.kylin.entity.KylinConfig;
import com.ilottery.kylin.entity.response.KylinJob;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by liush on 2017/6/7.
 */
public class ApplicationInfo {

    //@org.junit.Test
   // public void a() throws ParseException {
    public static void main(String[] args) throws ParseException{

        String appURL="http://name-122:8088/ws/";
        String appId="application_1496205871088_0200";
        Requset kylinClient=new Requset(appURL);
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        App app=kylinClient.getApplicationInfo(appId).getApp();
        System.out.println("===jobUuid===="+app.getProgress()+"==="+app.getFinalStatus());
        //System.out.println(JSON.toJSONString(kylinClient.buildCube(cubeConfig)));

      // scheduleAtFixedRate(scheduledThreadPool,kylinClient,jobUuid);
       /* KylinJob kylinJob=new KylinJob();
        kylinJob.setUuid(jobUuid);
        kylinJob=kylinClient.jobResume(kylinJob);
        String jobStatus=kylinJob.getJobStatus();
        System.out.println("===="+kylinJob.getRelatedCube());
        System.out.println(kylinJob.getName()+"========="+jobStatus);
        System.out.println("=================");*/
        //System.out.println(kylinClient.buildCube(cubeConfig));
    }


    private static void scheduleAtFixedRate(ScheduledExecutorService service,Requset client,String appId) {
        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                long start = new Date().getTime();
                System.out.println("scheduleAtFixedRate 开始执行时间:" +
                        DateFormat.getTimeInstance().format(new Date()));
                App app=client.getApplicationInfo(appId).getApp();
                String status= app.getFinalStatus();
                System.out.println("===="+app.getFinalStatus()+"==="+app.getProgress());
                if("FAILED".equals(status)||"KILL".equals(status)||"FINISHED".equals(status)){
                    service.shutdown();
                }
                long end = new Date().getTime();
                System.out.println("scheduleAtFixedRate 执行花费时间=" + (end - start) / 1000 + "m");
                System.out.println("scheduleAtFixedRate 执行完成时间："
                        + DateFormat.getTimeInstance().format(new Date()));
                System.out.println("======================================");
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }

}
