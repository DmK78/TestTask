package com.mdk78.testtask.quotes_list;

import com.mdk78.testtask.App;
import com.mdk78.testtask.model.Quote;
import com.mdk78.testtask.network.NetworkService;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class QuotesListPresenter implements IPresenter {
    public static String QUOTE_ID = "quote_id";
    private int offset = 1;
    private IView view;
    @Inject
    NetworkService networkService;
    private List<Quote> quotes = new ArrayList<>();
    private boolean isAllQuotesLoaded = false;

    public QuotesListPresenter() {
        App.getComponent().injectTo(this);
        networkService.setQuotesListLoadListener(quotes -> {
            if (quotes.size() == 0) {
                isAllQuotesLoaded = true;
            } else {
                QuotesListPresenter.this.quotes.addAll(quotes);
                view.setAdapterData(quotes);
                view.render(QuotesListPresenter.this.quotes);
            }
        });
    }

    public void attachView(IView quotesListView) {
        this.view = quotesListView;
    }

    public void detachView() {
        this.view = null;
    }

    public void setPageUp() {
        this.offset += 10;
    }

    public void getQuotesList() {
        if (!isAllQuotesLoaded || this.quotes.size()==0) {
            networkService.getQuotesList(10, offset);
        } else view.setAdapterData(this.quotes);

    }


    @Override
    public void onReachEndOfList() {
        if (!isAllQuotesLoaded) {
            view.showToast("Loading...");
            setPageUp();
            getQuotesList();
        }

    }


}
