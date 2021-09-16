package com.example.starBooks.ListView;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.starBooks.DetailBookListActivity;
import com.example.starBooks.R;
import com.example.starBooks.dto.Book;
import com.example.starBooks.databinding.BookItemListBinding;

import java.util.ArrayList;
import java.util.List;

public class ListViewAdapter extends BaseAdapter implements View.OnClickListener {

    private BookItemListBinding binding;
    private Context context;
    private List<Book> bookList;

    public ListViewAdapter(List<Book> bookList) {
        this.bookList= bookList;
    }

    @Override
    public int getCount() {
        return bookList.size();
    }

    @Override
    public Object getItem(int position) {
        return bookList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        context = parent.getContext();

        if (convertView==null) {
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            if (inflater != null) {
                convertView = inflater.inflate(R.layout.book_item_list,parent,false);
            }
        }

        Book book = bookList.get(pos);
        binding.tvBookId.setText(book.getId());
        binding.bookImage.setImageURI(loadImgUrl(book.getImgUrl()));
        binding.tvBookTitle.setText(book.getTitle());
        binding.tvAuthor.setText(book.getAuthor());
        binding.tvPublisher.setText(book.getPublisher());
        binding.tvPrice.setText(book.getPrice());
        binding.tvDate.setText(book.getCreateAt());
        binding.tvDescription.setText(book.getDescription());

        convertView.setOnClickListener(this);

        return convertView;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(context, DetailBookListActivity.class);
        context.startActivity(intent);
    }


    public Uri loadImgUrl(String imgUrl) {
        Uri uri = Uri.parse(imgUrl);
        return uri;

    }

    /*public BookItemListBinding binding;
    private List<Book> bookList;

    public ListViewAdapter() {
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

*/
}
