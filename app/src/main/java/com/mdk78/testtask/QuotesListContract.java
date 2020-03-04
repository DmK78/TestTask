package com.mdk78.testtask;

import com.mdk78.testtask.model.Quote;

import java.util.List;

public interface QuotesListContract {
    interface View {


    }

    interface Presenter {
        void onQuoteClicked(Quote quote);
        void onReachEndOfList();
    }

    interface Model{
        List<Quote> getQuotesList();
        Quote getQuote(int id);
    }
}
