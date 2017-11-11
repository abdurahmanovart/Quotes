package com.github.arturx.quotes.net;

import android.support.annotation.StringDef;

/**
 * @author arturx on 04/09/2017
 */

public class Categories {

    @StringDef({
            FAMOUS,
            MOVIES
    })
    public @interface Category {}

    public static final String FAMOUS = "famous";
    public static final String MOVIES = "movies";
}