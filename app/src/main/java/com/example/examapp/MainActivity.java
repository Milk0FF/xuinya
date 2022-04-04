package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.examapp.network.models.ApiHandler;
import com.example.examapp.network.models.ApiService;
import com.example.examapp.network.models.QuoteAdapter;
import com.example.examapp.network.models.QuoteList;
import com.example.examapp.network.models.QuoteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView quoteContainer;
    private TextView titleQuote;
    private TextView descriptionQuote;
    private List<QuoteResponse> quoteList;
    private ApiService service = ApiHandler.getInstance().getService();
    private SnapHelper snapHelper;
    private QuoteAdapter quoteAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteContainer = findViewById(R.id.quoteContainer);
        getQuotes();
    }

    private void getQuotes(){
        AsyncTask.execute(() -> {
            service.getQuotes().enqueue(new Callback<QuoteList>() {
                @Override
                public void onResponse(Call<QuoteList> call, Response<QuoteList> response) {
                    quoteList = response.body().getQuoteList();
                    if(response.isSuccessful()){
                        Toast.makeText(MainActivity.this, "Данные успешно получены!", Toast.LENGTH_SHORT).show();
                        quoteList.addAll(quoteList);
                        quoteAdapter = new QuoteAdapter(quoteList, getApplicationContext());

                        snapHelper = new PagerSnapHelper();
                        LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                        quoteContainer.setLayoutManager(manager);
                        quoteContainer.setAdapter(quoteAdapter);
                        snapHelper.attachToRecyclerView(quoteContainer);
                    }
                    else
                        Toast.makeText(MainActivity.this, "Произошла ошибка! Попробуйте позже!", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onFailure(Call<QuoteList> call, Throwable t) {
                    Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}