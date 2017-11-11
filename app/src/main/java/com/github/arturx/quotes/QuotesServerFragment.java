package com.github.arturx.quotes;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import com.github.arturx.quotes.adapter.QuoteAdapter;
import com.github.arturx.quotes.adapter.QuotesClickListener;
import com.github.arturx.quotes.bean.Quote;
import com.github.arturx.quotes.net.ApiClient;
import com.github.arturx.quotes.net.QuoteService;
import com.github.arturx.quotes.utils.Utils;

import java.util.Collections;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.github.arturx.quotes.net.Categories.FAMOUS;
import static com.github.arturx.quotes.net.Categories.MOVIES;

/**
 * @author arturx on 04/10/2017
 */

public class QuotesServerFragment extends BasicFragment implements QuotesClickListener {

    private OnQuoteServerClickListener mClickListener;
    private List<Quote> mQuotes;
    private QuoteAdapter mAdapter;
    private QuoteService mService;

    public static QuotesServerFragment newInstance() {
        return new QuotesServerFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnQuoteServerClickListener) {
            mClickListener = (OnQuoteServerClickListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnQuoteServerClickListener");
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mService = ApiClient.getClient().create(QuoteService.class);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getDataFromServer();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mClickListener = null;error
    }

    @Override
    protected void onSwipeRefresh() {
        getDataFromServer();
        onStopRefreshing();
    }

    @Override
    public void onQuoteClick(int position) {
        if (mClickListener != null) {
            mClickListener.onQuoteServerClick(mQuotes.get(position));
        }
    }

    private void getDataFromServer() {
        String category = Utils.readIsFamousChecked(getActivity()) ? FAMOUS : MOVIES;
        int count = Utils.readQuotesCount(getActivity());

        if (count == 1) {
            getOneQuote(category);
        } else {
            getQuotes(category, count);
        }
    }

    private void getOneQuote(String category) {
        Call<Quote> call = mService.getQuote(category, 1);
        call.enqueue(new Callback<Quote>() {
            @Override
            public void onResponse(Call<Quote> call, Response<Quote> response) {
                Quote quote = response.body();
                if (quote != null) {
                    mQuotes = Collections.singletonList(quote);
                    fillAdapter();
                }
            }

            @Override
            public void onFailure(Call<Quote> call, Throwable t) {
                handleFailure();
            }
        });
    }

    private void getQuotes(String category, int count) {
        Call<List<Quote>> listCall = mService.getQuotesList(category, count);
        listCall.enqueue(new Callback<List<Quote>>() {
            @Override
            public void onResponse(Call<List<Quote>> call, Response<List<Quote>> response) {
                List<Quote> responseList = response.body();
                if (responseList != null && !responseList.isEmpty()) {
                    mQuotes = responseList;
                    fillAdapter();
                }
            }

            @Override
            public void onFailure(Call<List<Quote>> call, Throwable t) {
                handleFailure();
            }
        });
    }

    private void handleFailure() {
        onStopRefreshing();
        Toast.makeText(getContext(), R.string.error, Toast.LENGTH_LONG).show();
    }

    private void fillAdapter() {
        mAdapter = new QuoteAdapter(mQuotes, this);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void onStopRefreshing() {
        mSwipeRefreshLayout.setRefreshing(false);
    }

    public interface OnQuoteServerClickListener {
        void onQuoteServerClick(Quote quote);
    }
}