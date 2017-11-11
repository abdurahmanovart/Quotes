package com.github.arturx.quotes.net;

import com.github.arturx.quotes.bean.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * @author arturx on 04/10/2017
 */

public interface QuoteService {

    @Headers("X-Mashape-Key: wZfS12TFbImshI6KAhgvl06keEFMp1rSiM8jsnCLbxtjKimVrl")
    @GET("/")
    Call<List<Quote>> getQuotesList(@Query("cat") @Categories.Category String cat,
                                    @Query("count") int count);

    @Headers("X-Mashape-Key: wZfS12TFbImshI6KAhgvl06keEFMp1rSiM8jsnCLbxtjKimVrl")
    @GET("/")
    Call<Quote> getQuote(@Query("cat") @Categories.Category String cat,
                         @Query("count") int count);
}