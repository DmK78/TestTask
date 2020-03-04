package com.mdk78.testtask.quotes_list;

import com.mdk78.testtask.App;
import com.mdk78.testtask.model.Quote;
import com.mdk78.testtask.network.NetworkService;
import com.mdk78.testtask.quote_details.QuoteDetailsActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class QuotesListPresenter {
    public static String QUOTE_ID = "quote_id";
    private int offset = 1;
    private QuotesListActivity view;
    @Inject
    NetworkService networkService;
    private List<Quote> quotes = new ArrayList<>();
    private boolean isAllQuotesLoaded = false;

    public QuotesListPresenter() {
        App.getComponent().injectTo(this);
        networkService.setQuotesListLoadListener(new NetworkService.QuotesListLoadListener() {
            @Override
            public void onQuotesListLoaded(List<Quote> quotes) {
                if (quotes.size() == 0) {
                    isAllQuotesLoaded = true;
                } else {
                    view.addDataToAdapter(quotes);
                }
            }
        });
    }

    public void attachView(QuotesListActivity quotesListActivity) {
        this.view = quotesListActivity;
    }

    public void detachView() {
        this.view = null;
    }

    public void setPageUp() {
        this.offset += 10;
    }

    public void getQuotesList() {
        if (this.quotes.size() == 0 || !isAllQuotesLoaded) {
            networkService.getQuotesList(10, offset);

        }
    }


    public void onQuoteClicked(Quote quote) {
        view.startActivity(QuoteDetailsActivity.create(view.getApplicationContext(),quote.id));

    }

    public void onReachEndOfListQuotes() {
        if (!isAllQuotesLoaded) {
            view.showToast("Loading...");
            setPageUp();
            getQuotesList();
        }
    }
}
