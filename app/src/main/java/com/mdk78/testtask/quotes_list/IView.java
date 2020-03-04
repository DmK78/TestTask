package com.mdk78.testtask.quotes_list;

import com.mdk78.testtask.model.Quote;

import java.util.List;

public interface IView {

        void render(List<Quote> quotes);
        void showToast(String s);
        void setAdapterData(List<Quote> quotes);



}
