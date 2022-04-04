package com.example.examapp.network.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class QuoteList {
    @SerializedName("data")
    private List<QuoteResponse> quoteList;

    public List<QuoteResponse> getQuoteList() {
        return quoteList;
    }

    public void setQuoteList(List<QuoteResponse> quoteList) {
        this.quoteList = quoteList;
    }
}
