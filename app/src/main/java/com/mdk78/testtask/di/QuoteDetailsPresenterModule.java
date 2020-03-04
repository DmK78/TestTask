package com.mdk78.testtask.di;

import com.mdk78.testtask.quote_details.QuoteDetailsPresenter;
import com.mdk78.testtask.quotes_list.QuotesListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class QuoteDetailsPresenterModule {
    @Singleton
    @Provides
    public QuoteDetailsPresenter providesQuoteDetailsPresenter() {
        return new QuoteDetailsPresenter();
    }
}
