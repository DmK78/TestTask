package com.mdk78.testtask.network;

import com.mdk78.testtask.model.Quote;

import java.util.List;

public interface IModel {
    void getQuotesList(int limit, int offset);

    void getQuote(int id);
}
