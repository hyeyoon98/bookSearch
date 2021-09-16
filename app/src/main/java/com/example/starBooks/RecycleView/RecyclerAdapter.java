package com.example.starBooks.RecycleView;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starBooks.dto.Book;
import com.example.starBooks.databinding.BookItemListBinding;

import java.util.ArrayList;
import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.BookViewHolder>{

    public BookItemListBinding binding;
    private List<Book> bookList;

    public RecyclerAdapter() {
        this.bookList = new ArrayList<>();
    }

    @NonNull
    @Override
    public BookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = BookItemListBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false);
        return new BookViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BookViewHolder holder, int position) {
        Book book = bookList.get(position);
        holder.bind(book);

    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    void setBookList(List<Book> bookList) {
        if (bookList != null) {
            this.bookList = bookList;
            notifyDataSetChanged();
        }
    }

    @BindingAdapter("bind:item")
    public static void bindItem(RecyclerView recyclerView, List<Book> bookList) {
        RecyclerAdapter adapter = (RecyclerAdapter) recyclerView.getAdapter();
        if (adapter != null) {
            adapter.setBookList(bookList);
        }
    }

    @BindingAdapter("bind:loadImage")
    public static void loadImage(ImageView imageView, String url) {
        Glide.with(imageView).load(url)
                .into(imageView);
    }

    class BookViewHolder extends RecyclerView.ViewHolder {

        BookItemListBinding binding;

        public BookViewHolder(BookItemListBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Book book) {
            binding.setBook(book);
        }
    }


}
