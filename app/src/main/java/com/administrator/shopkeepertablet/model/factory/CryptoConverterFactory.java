package com.administrator.shopkeepertablet.model.factory;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.reflect.TypeToken;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;

import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Converter;
import retrofit2.Retrofit;

/**
 * Description:处理response和request
 * Author CC
 * Time 2018/7/1
 */
public class CryptoConverterFactory extends Converter.Factory {

    private final Gson gson;

    public static CryptoConverterFactory create() {
        return create(new Gson());
    }

    public static CryptoConverterFactory create(Gson gson) {
        return new CryptoConverterFactory(gson);
    }

    private CryptoConverterFactory(Gson gson) {
        if (gson == null) throw new NullPointerException("gson == null");
        this.gson = gson;
    }

    @Override
    public Converter<ResponseBody, ?> responseBodyConverter(Type type, Annotation[] annotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new CryptoResponseBodyConverter<>(adapter);
    }

    @Override
    public Converter<?, RequestBody> requestBodyConverter(Type type, Annotation[] parameterAnnotations, Annotation[] methodAnnotations, Retrofit retrofit) {
        TypeAdapter<?> adapter = gson.getAdapter(TypeToken.get(type));
        return new CryptoRequestBodyConverter<>(gson, adapter);
    }
}
