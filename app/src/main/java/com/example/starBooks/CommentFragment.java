package com.example.starBooks;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.APIInterface.initMyApi;
import com.example.starBooks.adapter.CommentAdapter;
import com.example.starBooks.databinding.FragmentCommentBinding;
import com.example.starBooks.dto.Comment;
import com.example.starBooks.dto.CommentResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class CommentFragment extends Fragment {

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;
    private FragmentCommentBinding binding;
    private CommentAdapter adapter;
    private List<Comment> commentList = new ArrayList<>();
    private int bookId;

    public final String DATA_STORE = "DATA_STORE";

    public static CommentFragment newInstance() {
        Bundle bundle = new Bundle();
        CommentFragment fragment = new CommentFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getParentFragmentManager().setFragmentResultListener("bookId", this, new FragmentResultListener() {
            @Override public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle)
            {
                if (bundle.getInt("bookId") != 0) {
                    bookId = bundle.getInt("bookId");
                    System.out.println("----------------------------"+bookId);

                }

            }
        });



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comment,container,false);
        View root = binding.getRoot();

        adapter = new CommentAdapter(this,commentList);
        binding.recyclerViewComment.setLayoutManager(new LinearLayoutManager(getContext()));
        initCommentList(bookId);
        binding.recyclerViewComment.setAdapter(adapter);

        return root;
    }

    public void initCommentList(int bookId) {
        retrofitClient = RetrofitClient.getInstance();
        initMyApi=RetrofitClient.getRetrofitInterface();

        initMyApi.getCommentList(getPreferenceString("accessToken"),bookId).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CommentResponse result = response.body();

                    if (result.code.equals("E0008")) {

                        //리뷰
                        commentList = result.commentMap.getCommentList();
                        adapter = new CommentAdapter(CommentFragment.this, commentList);
                        binding.recyclerViewComment.setAdapter(adapter);

                        binding.avgStarRate.setText(String.format(Locale.US,"%.1f",result.commentMap.avgStarRate));
                        int avgStar = Math.round(result.commentMap.avgStarRate);

                        //평점
                        switch (avgStar) {
                            case 1:
                                binding.star1.setBackgroundResource(R.drawable.ic_star_solid);
                                break;

                            case 2:
                                binding.star1.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star2.setBackgroundResource(R.drawable.ic_star_solid);
                                break;

                            case 3:
                                binding.star1.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star2.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star3.setBackgroundResource(R.drawable.ic_star_solid);
                                break;

                            case 4:
                                binding.star1.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star2.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star3.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star4.setBackgroundResource(R.drawable.ic_star_solid);
                                break;

                            case 5:
                                binding.star1.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star2.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star3.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star4.setBackgroundResource(R.drawable.ic_star_solid);
                                binding.star5.setBackgroundResource(R.drawable.ic_star_solid);
                                break;
                        }

                    }


                }
            }

            @Override
            public void onFailure(Call<CommentResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void setPreference(String key, String value){
        SharedPreferences pref = getActivity().getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getPreferenceString(String key) {
        SharedPreferences pref = getActivity().getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        return pref.getString(key, "");
    }


}
