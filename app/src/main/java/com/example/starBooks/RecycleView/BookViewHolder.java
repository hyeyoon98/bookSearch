package com.example.starBooks.RecycleView;


import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.starBooks.DTO.Book;
import com.example.starBooks.databinding.BookItemListBinding;

public class BookViewHolder extends RecyclerView.ViewHolder {

    BookItemListBinding binding;

    public BookViewHolder(@NonNull View itemView) {
        super(itemView);
        binding = DataBindingUtil.bind(itemView);
    }

    public void onBindView(BookViewHolder holder, int position) {
       /* Book book = getItem(position);
        holder.binding.setBook(book);*/
    }


    @BindingAdapter("bind:imageUrl")
    public static void loadImage(ImageView view, String imgUrl) {
        Glide.with(view.getContext())
                .load(imgUrl).apply(new RequestOptions().centerInside())
                .into(view);
    }
}
