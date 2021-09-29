package com.example.starBooks.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starBooks.BR;
import com.example.starBooks.DetailBookListActivity;
import com.example.starBooks.R;
import com.example.starBooks.databinding.BookItemListBinding;
import com.example.starBooks.dto.Book;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Book> bookList;
    private Activity activity;

    public MainAdapter (Activity activity, List<Book> bookList) {
        this.activity = activity;
        this.bookList = bookList;
        System.out.println("bookList Adapter>>>" + bookList);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        BookItemListBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),R.layout.book_item_list,parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        Book book = bookList.get(position);
        holder.bind(book);

        System.out.println("book >>>" + book);
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        BookItemListBinding binding;

        public ViewHolder(@NonNull BookItemListBinding binding) {
            super(binding.getRoot());
            this.binding=binding;
            binding.constraintLayout.setOnClickListener(this);
        }

        public void bind(Object object) {
            binding.setVariable(BR.book, object);
            binding.executePendingBindings();
        }


        @Override
        public void onClick(View v) {

            int pos = getBindingAdapterPosition();
            if (pos != RecyclerView.NO_POSITION) {

                notifyDataSetChanged();

                Intent intent = new Intent(activity, DetailBookListActivity.class);
                intent.putExtra("book_id", bookList.get(pos).getId());
                activity.startActivity(intent);
            }



        }
    }

}
