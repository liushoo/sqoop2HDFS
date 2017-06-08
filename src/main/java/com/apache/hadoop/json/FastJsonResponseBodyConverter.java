package com.ilottery.kylin.json;

import com.alibaba.fastjson.JSON;
import com.squareup.okhttp.ResponseBody;
import okio.BufferedSource;
import okio.Okio;
import retrofit.Converter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * Created by hxd on 2017/6/7.
 */
public class FastJsonResponseBodyConverter<T> implements Converter<ResponseBody,T> {
    private final Type type;

    public FastJsonResponseBodyConverter(Type type) {
        this.type = type;
    }

    /*
    * 转换方法
    */
    @Override
    public T convert(ResponseBody value) throws IOException  {
        BufferedSource bufferedSource = Okio.buffer(value.source());
        String tempStr = bufferedSource.readUtf8();
        bufferedSource.close();
        return JSON.parseObject(tempStr, type);
    }
}
