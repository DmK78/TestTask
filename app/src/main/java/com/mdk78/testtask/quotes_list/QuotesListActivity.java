package com.mdk78.testtask.quotes_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.mdk78.testtask.App;

import com.mdk78.testtask.databinding.ActivityQuotesListBinding;
import com.mdk78.testtask.model.Quote;
import com.mdk78.testtask.quote_details.QuoteDetailsActivity;

import java.util.List;

import javax.inject.Inject;


public class QuotesListActivity extends AppCompatActivity implements IView {
    @Inject
    QuotesListPresenter presenter;
    private ActivityQuotesListBinding binding;
    private QuotesAdapter adapter;
    private Parcelable listState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState!=null){
            listState=savedInstanceState.getParcelable("ListState");
        }
        App.getComponent().injectTo(this);
        binding = ActivityQuotesListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter.attachView(this);
        adapter = new QuotesAdapter();
        binding.recyclerQuotes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerQuotes.setAdapter(adapter);
        binding.recyclerQuotes.getLayoutManager().onRestoreInstanceState(listState);
        presenter.getQuotesList();
        adapter.setOnClickListener(quote -> startActivity(QuoteDetailsActivity.create(this, quote.id)));
        adapter.setOnReachOfEndListener(() -> {
            presenter.onReachEndOfList();
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("ListState", binding.recyclerQuotes.getLayoutManager().onSaveInstanceState());

    }

    @Override
    public void showToast(String s) {
        Toast.makeText(QuotesListActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    /*public void addDataToAdapter(List<Quote> quotes) {
        adapter.addData(quotes);
    }*/

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    public void setAdapterData(List<Quote>quotes){
        adapter.addData(quotes);
    }

    @Override
    public void render(List<Quote> quotes) {

        //adapter.addData(quotes);
    }
}
