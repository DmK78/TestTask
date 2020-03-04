package com.mdk78.testtask.quote_details;

import com.mdk78.testtask.App;
import com.mdk78.testtask.model.Quote;
import com.mdk78.testtask.network.NetworkService;

import java.util.List;

import javax.inject.Inject;

public class QuoteDetailsPresenter {
    @Inject
    NetworkService networkService;
    QuoteDetailsActivity view;

    public QuoteDetailsPresenter() {
        App.getComponent().injectTo(this);

        networkService.setQuoteDetailsLoadListener(new NetworkService.QuoteDetailsLoadListener() {
            @Override
            public void onQuoteLoaded(Quote quote) {
                view.renderView(quote);
            }
        });

    }

    public void attachView(QuoteDetailsActivity quoteDetailsActivity) {
        this.view = quoteDetailsActivity;
    }

    public void detachView() {
        this.view = null;
    }

    public void loadQuote(int id){
        networkService.getQuote(id);

    }
}
