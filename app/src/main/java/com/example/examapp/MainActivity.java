package com.example.examapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;

import com.example.examapp.network.models.ApiHandler;
import com.example.examapp.network.models.ApiService;
import com.example.examapp.network.models.QuoteResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView quoteContainer;
    private TextView titleQuote;
    private TextView descriptionQuote;
    private ApiService service = ApiHandler.getInstance().getService();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        quoteContainer = findViewById(R.id.quoteContainer);
    }

    private void getQuotes(){
        AsyncTask.execute(() -> {
            service.getQuotes().enqueue(new Callback<List<QuoteResponse>>() {
                @Override
                public void onResponse(Call<List<QuoteResponse>> call, Response<List<QuoteResponse>> response) {
                    if(response.isSuccessful()){
                        
                    }

                }

                @Override
                public void onFailure(Call<List<QuoteResponse>> call, Throwable t) {

                }
            });
        });
    }
}