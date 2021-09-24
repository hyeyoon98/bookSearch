package com.example.starBooks.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starBooks.BR;
import com.example.starBooks.R;
import com.example.starBooks.databinding.BookItemListBinding;
import com.example.starBooks.dto.Book;

import java.util.ArrayList;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> implements View.OnClickListener {

    private ArrayList<Book> bookArrayList;
    private Activity activity;

    public MainAdapter (Activity activity, ArrayList<Book> bookArrayList) {
        this.activity = activity;
        this.bookArrayList = bookArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.book_item_list,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Book book = bookArrayList.get(position);
        holder.bind(book);
        holder.binding.setOnClick(this);

    }

    @Override
    public int getItemCount() {
        return bookArrayList.size();
    }


    @Override
    public void onClick(View v) {

        Toast.makeText(activity, "You clicked " ,
                Toast.LENGTH_LONG).show();

    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        BookItemListBinding binding;

        public ViewHolder(@NonNull BookItemListBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
        }

        public void bind(Object object) {
            binding.setVariable(BR.book, object);
            binding.executePendingBindings();
        }
    }
}
