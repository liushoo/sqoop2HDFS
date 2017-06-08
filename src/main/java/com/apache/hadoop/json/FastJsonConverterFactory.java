package com.ilottery.kylin.json;

import com.squareup.okhttp.RequestBody;
import com.squareup.okhttp.ResponseBody;
import retrofit.Converter;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

/**
 * Created by hxd on 2017/6/7.
 */
public final class FastJsonConverterFactory<T> extends Converter.Factory{

    public static FastJsonConverterFactory create() {
        return new FastJsonConverterFactory();
    }

    @Override
    public Converter<ResponseBody, T> fromResponseBody(Type type, Annotation[] annotations) {
        return new FastJsonResponseBodyConverter<T>(type);
    }

    @Override
    public Converter<T, RequestBody> toRequestBody(Type type, Annotation[] annotations) {
        return new FastJsonRequestBodyConverter<T>();
    }
}
