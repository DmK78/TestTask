package com.mdk78.testtask.network;


import com.mdk78.testtask.model.Quote;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface JsonPlaceHolderApi {
    @GET("test")
    Call<List<Quote>> getQuotesList(
            @Query("limit") int limit,
            @Query("offset") int offset
    );

    @GET("test/{id}")
    Call<Quote> getQuoteDetails(@Path("id") int id);


}
