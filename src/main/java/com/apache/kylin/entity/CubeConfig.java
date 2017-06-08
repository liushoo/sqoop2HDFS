package org.apache.hadoop.kylin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by liush on 2017/6/6.
 */

@Setter
@Getter
@AllArgsConstructor
public class CubeConfig {

    private Long startTime;

    private Long endTime;

    private BuildType buildType;
}
