package org.apache.hadoop.kylin.entity.response;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Created by liush on 17-6-7.
 */
@Getter
@Setter
public class KylinJob {
    private String uuid;
    @JSONField(name = "last_modified")
    private String lastModified;
    private String name;
    private String type;
    private int duration;
    @JSONField(name = "related_cube")
    private String relatedCube;
    @JSONField(name = "related_segment")
    private String relatedSegment;
    @JSONField(name = "exec_start_time")
    private String execStartTime;
    @JSONField(name = "exec_end_time")
    private String execEndTime;
    @JSONField(name = "mr_waiting")
    private String mrWaiting;
    private List<Steps> steps;

    @JSONField(name = "job_status")
    private String jobStatus;
    private float progress;
}
