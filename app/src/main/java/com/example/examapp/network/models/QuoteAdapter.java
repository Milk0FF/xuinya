package com.example.examapp.network.models;


import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.examapp.R;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class QuoteAdapter extends RecyclerView.Adapter<QuoteAdapter.ViewHolder> {

    private List<QuoteResponse> quotesList;
    private LayoutInflater inflater;
    private Context context;

    public QuoteAdapter(List<QuoteResponse> quoteResponse, Context context) {
        this.quotesList = quoteResponse;
        this.inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public QuoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull QuoteAdapter.ViewHolder holder, int position) {
        QuoteResponse singleQuote = quotesList.get(position);
        holder.setQuoteTitle(singleQuote.getTitle());
        holder.setQuoteDescription(singleQuote.getDescription());
        holder.setQuoteImage(singleQuote.getImageUrl());
    }

    @Override
    public int getItemCount() {
        return quotesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        final private ImageView quoteImage;
        final private TextView quoteTitle;
        final private TextView quoteDescription;

        private ViewHolder(View view) {
            super(view);
            this.quoteDescription = (TextView) view.findViewById(R.id.quoteDescription);
            this.quoteImage = (ImageView) view.findViewById(R.id.quoteImage);
            this.quoteTitle = (TextView) view.findViewById(R.id.quoteTitle);
        }

        public void setQuoteImage(String image) {
            Picasso.with(context)
                    .load(image)
                    .into(quoteImage);
        }
        public void setQuoteTitle(String title) {
            this.quoteTitle.setText(title);
        }

        public void setQuoteDescription(String desc) {
            this.quoteDescription.setText(desc);
        }
    }
}

