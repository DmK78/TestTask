package com.mdk78.testtask.quote_details;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.mdk78.testtask.App;
import com.mdk78.testtask.databinding.ActivityQuoteDetailsBinding;
import com.mdk78.testtask.model.Quote;
import com.mdk78.testtask.quotes_list.QuotesListPresenter;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.inject.Inject;

public class QuoteDetailsActivity extends AppCompatActivity implements IView{
    @Inject
    QuoteDetailsPresenter presenter;
    private ActivityQuoteDetailsBinding binding;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        App.getComponent().injectTo(this);
        binding = ActivityQuoteDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        presenter.attachView(this);
        int id = getIntent().getIntExtra(QuotesListPresenter.QUOTE_ID, 0);
        presenter.loadQuote(id);
    }

    public void renderView(Quote quote) {
        /*SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss_dd:MM:yyyy", Locale.getDefault());
        String currentDateAndTime = sdf.format(new Date());*/
        binding.tvQuoteDetCreated.setText(quote.getCreated());
        binding.tvQuoteDetText.setText(quote.text);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, quote.tagList) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                View view = super.getView(position, convertView, parent);
                if (position % 2 == 1) {
                    view.setBackgroundColor(Color.parseColor("#FFB6B546"));
                } else {
                    view.setBackgroundColor(Color.parseColor("#FFCCCB4C"));
                }
                return view;
            }
        };
        binding.listViewQuotes.setAdapter(arrayAdapter);
    }

    public static Intent create(Context context, int id) {
        Intent intent = new Intent(context, QuoteDetailsActivity.class);
        intent.putExtra(QuotesListPresenter.QUOTE_ID, id);
        return intent;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }
}
