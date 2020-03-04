package com.mdk78.testtask.network;

import android.util.Log;


import com.mdk78.testtask.model.Quote;

import java.util.List;
import java.util.Objects;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService {
    private Retrofit mRetrofit;
    private static String BASE_URL = "https://oico.app/";
    private QuotesListLoadListener quotesListLoadListener;
    private QuoteDetailsLoadListener quoteDetailsLoadListener;

    public NetworkService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
    }

    public void setQuotesListLoadListener(QuotesListLoadListener quoteLoadListener){
        this.quotesListLoadListener = quoteLoadListener;
    }

    public void setQuoteDetailsLoadListener(QuoteDetailsLoadListener quoteDetailsLoadListener){
        this.quoteDetailsLoadListener = quoteDetailsLoadListener;
    }


    public void getQuotesList(int limit, int offset) {

        getJSONApi().getQuotesList(limit, offset)
                .enqueue(new Callback<List<Quote>>() {

                    @Override
                    public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                        if (response.body() != null) {
                            quotesListLoadListener.onQuotesListLoaded(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Quote>> call, Throwable t) {
                        Log.i(getClass().getName(), Objects.requireNonNull(t.getMessage()));
                    }
                });
    }

    public void getQuoteDetails(int id) {

        getJSONApi().getQuoteDetails(id)
                .enqueue(new Callback<Quote>() {

                    @Override
                    public void onResponse(Call<Quote> call, Response<Quote> response) {
                        if (response.body() != null) {
                            quoteDetailsLoadListener.onQuoteLoaded(response.body());
                        }
                    }

                    @Override
                    public void onFailure(Call<Quote> call, Throwable t) {
                        Log.i(getClass().getName(), Objects.requireNonNull(t.getMessage()));
                    }
                });
    }

    public JsonPlaceHolderApi getJSONApi() {
        return mRetrofit.create(JsonPlaceHolderApi.class);
    }

    public interface QuotesListLoadListener {
        void onQuotesListLoaded(List<Quote> quotes);
    }
    public interface QuoteDetailsLoadListener{
        void onQuoteLoaded(Quote quote);
    }
}