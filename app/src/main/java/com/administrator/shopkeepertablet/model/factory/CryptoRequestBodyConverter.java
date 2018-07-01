package com.administrator.shopkeepertablet.model.factory;

import com.administrator.shopkeepertablet.utils.MLog;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;
import java.nio.charset.Charset;


import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Converter;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */
public class CryptoRequestBodyConverter<T> implements Converter<T, RequestBody> {
    private static final MediaType MEDIA_TYPE = MediaType.parse("application/json; charset=UTF-8");
    private static final Charset UTF_8 = Charset.forName("UTF-8");

    private final Gson gson;
    private final TypeAdapter<T> adapter;

    CryptoRequestBodyConverter(Gson gson, TypeAdapter<T> adapter) {
        this.gson = gson;
        this.adapter = adapter;
    }

    @Override
    public RequestBody convert(T value) throws IOException {
        String string = gson.toJson(value);
        MLog.d("api", "request param : " + string);
        return RequestBody.create(MEDIA_TYPE, string);
    }
}
