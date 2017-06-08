package com.ilottery.hadoop.entity;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by liush on 17-6-8.
 */
@Getter
@Setter
public class App {
    private Long finishedTime;
    private String amContainerLogs;
    private String trackingUI;
    private String user;
    private String id;
    private String clusterId;
    private String finalStatus;
    private String amHostHttpAddress;
    private Float progress;
    private String name;
    private Long startedTime;
    private Long elapsedTime;
    private String diagnostics;
    private String trackingUrl;
    private String queue;
}
