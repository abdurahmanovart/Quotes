package com.github.arturx.quotes.dagger;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.github.arturx.quotes.net.QuoteService;
import com.github.arturx.quotes.utils.SharedPrefManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import retrofit2.Converter;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * @author arturx on 10/10/2017
 */

@Module
public class NetModule {

    private String mBaseUlr;

    public NetModule(String baseUlr) {
        mBaseUlr = baseUlr;
    }

    @Provides
    @Singleton
    SharedPreferences provideSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    SharedPrefManager provideSharedPrefManager(SharedPreferences preferences) {
        return new SharedPrefManager(preferences);
    }

    @Provides
    @Singleton
    Converter.Factory provideJacksonConverterFactory() {
        return JacksonConverterFactory.create();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(Converter.Factory jacksonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(mBaseUlr)
                .addConverterFactory(jacksonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    QuoteService provideService(Retrofit retrofit) {
        return retrofit.create(QuoteService.class);
    }

    @Provides
    @Singleton
    Realm provideRealm(Application application) {
        return Realm.getInstance(application);
    }
}