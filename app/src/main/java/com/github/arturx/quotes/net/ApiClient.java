package com.github.arturx.quotes.net;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by arturx on 06.10.17.
 */

public class ApiClient {

    private static final String BASE_URL = "https://abdruxnet-random-famous-quotes.p.mashape.com";
    private static Retrofit sRetrofit;

    private ApiClient() {
        throw new IllegalStateException("can't create object");
    }

    public static Retrofit getClient() {
        if (sRetrofit == null) {
            sRetrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(JacksonConverterFactory.create())
                    .build();
        }
        return sRetrofit;
    }
}