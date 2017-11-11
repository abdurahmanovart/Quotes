package com.github.arturx.quotes.dagger;

import com.github.arturx.quotes.MainActivity;
import com.github.arturx.quotes.QuotesRealmFragment;
import com.github.arturx.quotes.QuotesServerFragment;
import com.github.arturx.quotes.widget.QuoteWidgetProvider;

import javax.inject.Singleton;

import dagger.Component;

/**
 * @author arturx on 10/10/2017
 */

@Singleton
@Component(modules = {AppModule.class, NetModule.class})
public interface NetComponent {

    void inject(MainActivity activity);

    void inject(QuotesRealmFragment fragment);

    void inject(QuotesServerFragment fragment);

    void inject(QuoteWidgetProvider provider);
}