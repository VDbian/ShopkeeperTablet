package com.administrator.shopkeepertablet.di.app;

import com.administrator.shopkeepertablet.model.api.ApiSource;
import com.administrator.shopkeepertablet.model.api.ApiSourceImpl;
import com.administrator.shopkeepertablet.model.factory.CryptoConverterFactory;
import com.administrator.shopkeepertablet.model.preference.PreferenceSource;
import com.administrator.shopkeepertablet.utils.MLog;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;


import dagger.Module;
import dagger.Provides;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Description:
 * Author CC
 * Time 2018/6/11
 */
@Module
public class NetworkModule {
    public static final String PHOTO_TOKEN = "";
    private static final String BASE_URL = "https://www.xcyytc.com:8098/";
    private static final String BASE_URL_PHOTO = "";
    private static final String TAG = "api";

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(final PreferenceSource preferenceSource) {
        final OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();
        okHttpClient.connectTimeout(20, TimeUnit.SECONDS);
        okHttpClient.readTimeout(5,TimeUnit.SECONDS);
        okHttpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();
//                MLog.d(TAG, "request token " + preferenceSource.getToken());
                MLog.d(TAG, "request url " + original.url());
//                if (!preferenceSource.getToken().equals("")) {
//                    Request request = original.newBuilder()
//                            .header("Authorization", "Bearer " + preferenceSource.getToken())
//                            .build();
//                    return chain.proceed(request);
//                }
                return chain.proceed(original);
            }
        });
        return okHttpClient.build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(CryptoConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    @Provides
    @Singleton
    ApiSource provideApiSource(Retrofit retrofit) {
        return new ApiSourceImpl(retrofit);
    }

//    @Provides
//    @Singleton
//    ApiPhoto provideApiPhoto() {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL_PHOTO)
//                .addConverterFactory(CryptoConverterFactory.create())
//                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
//                .client(new OkHttpClient.Builder().connectTimeout(20, TimeUnit.SECONDS)
//                        .addInterceptor(new Interceptor() {
//                            @Override
//                            public Response intercept(Chain chain) throws IOException {
//                                Request original = chain.request();
//                                MLog.d(TAG, "request url " + original.url());
//                                Request request = original.newBuilder()
//                                        .header("RainbowKey", PHOTO_TOKEN)
//                                        .build();
//                                return chain.proceed(request);
//                            }
//                        }).build())
//                .build();
//        return new ApiPhotoImpl(retrofit);
//    }

}
