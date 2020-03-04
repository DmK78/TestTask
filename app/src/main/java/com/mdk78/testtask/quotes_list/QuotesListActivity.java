package com.mdk78.testtask.quotes_list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.widget.Toast;

import com.mdk78.testtask.App;

import com.mdk78.testtask.databinding.ActivityQuotesListBinding;
import com.mdk78.testtask.model.Quote;

import java.util.List;

import javax.inject.Inject;


public class QuotesListActivity extends AppCompatActivity {
    @Inject
    QuotesListPresenter presenter;
    private ActivityQuotesListBinding binding;
    private QuotesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().injectTo(this);
        binding = ActivityQuotesListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter.attachView(this);
        adapter = new QuotesAdapter();
        binding.recyclerQuotes.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        binding.recyclerQuotes.setAdapter(adapter);
        presenter.getQuotesList();
        adapter.setOnClickListener(quote -> presenter.onQuoteClicked(quote));
        adapter.setOnReachOfEndListener(() -> {
            presenter.onReachEndOfListQuotes();
        });

    }

    public void showToast(String s) {
        Toast.makeText(QuotesListActivity.this, s, Toast.LENGTH_SHORT).show();
    }

    public void addDataToAdapter(List<Quote> quotes) {
        adapter.addData(quotes);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
