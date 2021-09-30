package com.example.starBooks.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.starBooks.BR;
import com.example.starBooks.R;
import com.example.starBooks.databinding.CommentItemListBinding;
import com.example.starBooks.databinding.FragmentCommentBinding;
import com.example.starBooks.dto.Book;
import com.example.starBooks.dto.Comment;
import com.example.starBooks.dto.CommentResponse;

import java.util.List;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder> {

    private List<Comment> commentList;
    private Context context;

    public CommentAdapter (Context context, List<Comment> commentList) {
        this.context = context;
        this.commentList = commentList;
        System.out.println("bookList Adapter>>>" + commentList);
    }


    @NonNull
    @Override
    public CommentAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        FragmentCommentBinding binding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.comment_item_list,parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull CommentAdapter.ViewHolder holder, int position) {
        Comment comment = commentList.get(position);
        holder.bind(comment);
    }

    @Override
    public int getItemCount() {
        return commentList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        CommentItemListBinding binding;

        public ViewHolder(@NonNull FragmentCommentBinding binding) {
            super(binding.getRoot());
        }

        public void bind(Object object) {
            binding.setVariable(BR.comment, object);
            binding.executePendingBindings();
        }


    }
}
