package com.mdk78.testtask.di;

import com.mdk78.testtask.network.NetworkService;
import com.mdk78.testtask.quotes_list.QuotesListPresenter;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class QuotesListPresenterModule {
    @Singleton
    @Provides
    public QuotesListPresenter providesQuoteListPresenter() {
        return new QuotesListPresenter();
    }
}
