package com.administrator.shopkeepertablet.model.factory;

import com.administrator.shopkeepertablet.utils.MLog;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Converter;

/**
 * Description:
 * Author CC
 * Time 2018/7/1
 */
public class CryptoResponseBodyConverter<T> implements Converter<ResponseBody, T> {

    private final TypeAdapter<T> adapter;

    CryptoResponseBodyConverter(TypeAdapter<T> adapter) {
        this.adapter = adapter;
    }

    @Override
    public T convert(ResponseBody value) throws IOException {
        String result = value.string();
//        String body = result.replace("/","");
        MLog.d("api", "response value : " + result);
        return adapter.fromJson(result);
    }
}
