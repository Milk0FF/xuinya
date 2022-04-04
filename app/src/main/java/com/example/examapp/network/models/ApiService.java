package com.example.examapp.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("/quotes")
    Call<List<QuoteResponse>> getQuotes();
}
