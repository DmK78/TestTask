package com.mdk78.testtask.di;


import com.mdk78.testtask.quote_details.QuoteDetailsActivity;
import com.mdk78.testtask.quote_details.QuoteDetailsPresenter;
import com.mdk78.testtask.quotes_list.QuotesListActivity;
import com.mdk78.testtask.quotes_list.QuotesListPresenter;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {NetworkServiceModule.class, QuotesListPresenterModule.class, QuoteDetailsPresenterModule.class})
public interface AppComponent {
    void injectTo(QuotesListPresenter quotesListPresenter);
    void injectTo(QuotesListActivity quotesListActivity);
    void injectTo(QuoteDetailsActivity quoteDetailsActivity);
    void injectTo(QuoteDetailsPresenter quoteDetailsPresenter);




}
