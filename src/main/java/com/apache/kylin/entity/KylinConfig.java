package com.hadoop.kylin.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by hxd on 2017/6/6.
 */

@Setter
@Getter
@AllArgsConstructor
public class KylinConfig {

    private String url;

    private String cubeName;

    private String authorization;
}
