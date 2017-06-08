import com.alibaba.fastjson.JSON;
import org.apache.hadoop.kylin.Main;
import org.apache.hadoop.kylin.client.KylinClient;
import org.apache.hadoop.kylin.entity.BuildType;
import org.apache.hadoop.kylin.entity.CubeConfig;
import org.apache.hadoop.kylin.entity.KylinConfig;
import org.apache.hadoop.kylin.entity.response.KylinJob;
import org.junit.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Created by hxd on 2017/6/5.
 */
public class ATest {

    //@org.junit.Test
   // public void a() throws ParseException {
    public static void main(String[] args) throws ParseException{
        KylinConfig kylinConfig=new KylinConfig("http://192.168.110.81:7070/kylin/","ky1in_sala","Basic QURNSU46S1lMSU4=");
        KylinClient kylinClient=new KylinClient(kylinConfig);
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(10);
        String begin="2013-11-01";
        String end="2013-12-01";
        Calendar calst = Calendar.getInstance();;
        Calendar caled = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        calst.setTime(sdf.parse(begin));
        caled.setTime(sdf.parse(end));
        CubeConfig cubeConfig=new CubeConfig(calst.getTimeInMillis(),caled.getTimeInMillis(), BuildType.BUILD);
        String jobUuid=kylinClient.buildCube(cubeConfig).getUuid();
        System.out.println("===jobUuid===="+jobUuid);
        //System.out.println(JSON.toJSONString(kylinClient.buildCube(cubeConfig)));

       scheduleAtFixedRate(scheduledThreadPool,kylinClient,jobUuid);
       /* KylinJob kylinJob=new KylinJob();
        kylinJob.setUuid(jobUuid);
        kylinJob=kylinClient.jobResume(kylinJob);
        String jobStatus=kylinJob.getJobStatus();
        System.out.println("===="+kylinJob.getRelatedCube());
        System.out.println(kylinJob.getName()+"========="+jobStatus);
        System.out.println("=================");*/
        //System.out.println(kylinClient.buildCube(cubeConfig));
    }


    private static void scheduleAtFixedRate(ScheduledExecutorService service,KylinClient client,String jobuuid) {

        service.scheduleAtFixedRate(new Runnable() {
            @Override
            public void run() {
                KylinConfig kylinConfig=new KylinConfig("http://192.168.100.121:7070/kylin/","kylin1_sala","Basic QURNSU46S1lMSU4=");
                KylinClient kylinClient=new KylinClient(kylinConfig);

                long start = new Date().getTime();
                System.out.println("scheduleAtFixedRate 开始执行时间:" +
                        DateFormat.getTimeInstance().format(new Date()));
                KylinJob kylinJob=new KylinJob();
                kylinJob.setUuid(jobuuid);
                kylinJob=client.jobResume(kylinJob);
                String jobStatus=kylinJob.getJobStatus();
                System.out.println("===="+kylinJob.getRelatedCube());
                System.out.println(kylinJob.getName()+"========="+jobStatus);
                if("STOPPED ".equals(jobStatus)||"FINISHED".equals(jobStatus)||"DISCARDED".equals(jobStatus)){
                    service.shutdown();
                }
           /*     try {
                    Thread.sleep(sleepTime);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
                long end = new Date().getTime();
                System.out.println("scheduleAtFixedRate 执行花费时间=" + (end - start) / 1000 + "m");
                System.out.println("scheduleAtFixedRate 执行完成时间："
                        + DateFormat.getTimeInstance().format(new Date()));
                System.out.println("======================================");
            }
        }, 1000, 5000, TimeUnit.MILLISECONDS);
    }

}
