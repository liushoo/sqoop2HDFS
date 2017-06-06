package com.apache.sqoop;

import com.apache.sqoop.demo.util.SqoopJobInit;
import com.quartz.core.Mouse;

/**
 * Created by houlongbin on 2016/11/1.
 * liushuhua
 */
public class Main {
    public static void main(String[] args) throws Exception{
        SqoopJobInit.init();
        Mouse.run();
    }

}
