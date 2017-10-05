package com.github.arturx.quotes.net;

import android.support.annotation.StringDef;

/**
 * Created by arturx on 06.10.17.
 */

public class Categories {

    public static final String FAMOUS = "famous";
    public static final String MOVIES = "movies";

    @StringDef({FAMOUS,
            MOVIES})

    public @interface Category {}
}