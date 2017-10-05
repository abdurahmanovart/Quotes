package com.github.arturx.quotes.net;

import com.github.arturx.quotes.bean.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

/**
 * Created by arturx on 06.10.17.
 */

public interface QuoteService {

    @Headers("X-mashape-Key: /*cluchtuta*/")
    @GET
    Call<List<Quote>> getQuotesList(@Query("cat") @Categories.Category String cat,
                                    @Query("count") int count);

}