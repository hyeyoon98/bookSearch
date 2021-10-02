package com.example.starBooks;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.APIInterface.initMyApi;
import com.example.starBooks.adapter.CommentAdapter;
import com.example.starBooks.databinding.FragmentCommentBinding;
import com.example.starBooks.dto.Comment;
import com.example.starBooks.dto.CommentRegisterRequest;
import com.example.starBooks.dto.CommentResponse;
import com.example.starBooks.dto.ResponseMessage;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;
import static java.lang.Float.NaN;

public class CommentFragment extends Fragment implements View.OnClickListener {

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;
    private FragmentCommentBinding binding;
    private CommentAdapter adapter;
    private List<Comment> commentList = new ArrayList<>();

    private int bookId;
    private int starRateScore = 0;
    private String commentFinal = "";

    public final String DATA_STORE = "DATA_STORE";

    private static final String BUNDLE_PARAM = "book_Id";

    public static CommentFragment newInstance(int bookId) {
        Bundle bundle = new Bundle();
        CommentFragment fragment = new CommentFragment();
        bundle.putInt(BUNDLE_PARAM, bookId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            bookId = getArguments().getInt(BUNDLE_PARAM);
        }


        initCommentList(bookId);



    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_comment,container,false);
        View root = binding.getRoot();
        binding.setOnClick(this);

        return root;
    }

    public void initCommentList(int bookId) {
        retrofitClient = RetrofitClient.getInstance();
        initMyApi=RetrofitClient.getRetrofitInterface();

        String token;

        initMyApi.getCommentList(bookId).enqueue(new Callback<CommentResponse>() {
            @Override
            public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    CommentResponse result = response.body();

                    if (result.code.equals("E0008")) {

                        //리뷰
                        commentList = result.commentMap.getCommentList();
                        if (commentList != null) {
                            binding.recyclerViewComment.setVisibility(View.VISIBLE);
                            adapter = new CommentAdapter(getActivity(), commentList);
                            binding.recyclerViewComment.setAdapter(adapter);
                        } else {
                            binding.tvFirstReview.setVisibility(View.VISIBLE);
                        }

                        if (result.commentMap.getAvgStarRate() == NaN) {
                            binding.avgStarRate.setText("0.0");
                        } else {

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

    public long getPreferenceLong(String key) {
        SharedPreferences pref = getActivity().getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        return pref.getLong(key, 0);
    }

    public void deletePreference(String key) {
        SharedPreferences pref = getActivity().getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }


    @Override
    public void onClick(View v) {

        //토큰만료시간
        long expirationTime = ((System.currentTimeMillis())-getPreferenceLong("tokenExpiresIn"))/1000;

        if (v.getId() == R.id.button_review) {
            if (getPreferenceString("accessToken") == null) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("알림")
                        .setMessage("현재 비회원 상태입니다. 로그인하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("취소", null)
                        .create()
                        .show();
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.dismiss();
            } else if (getPreferenceLong("accessTokenExpireIn") < expirationTime){
                deletePreference("accessToken");
                deletePreference("refreshToken");


                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("알림")
                        .setMessage("로그인이 만료되었습니다. 다시 로그인하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(getContext(), LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("취소", null)
                        .create()
                        .show();
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.dismiss();
            } else {
                CustomDialog customDialog = new CustomDialog(getContext(), new CustomDialog.CustomDialogListener() {

                    @Override
                    public void onStar1Click(int starRate) {
                        starRateScore = starRate;
                    }

                    @Override
                    public void onStar2Click(int starRate) {
                        starRateScore = starRate;

                    }

                    @Override
                    public void onStar3Click(int starRate) {
                        starRateScore = starRate;

                    }

                    @Override
                    public void onStar4Click(int starRate) {
                        starRateScore = starRate;

                    }

                    @Override
                    public void onStar5Click(int starRate) {
                        starRateScore = starRate;

                    }

                    @Override
                    public void onRegisterClick(String comment) {
                        commentFinal = comment;
                        if (commentFinal.trim().equals("")) {
                            showAlert("리뷰를 작성해주세요.");
                        } else if (starRateScore == 0) {
                            showAlert("별점을 입력해주세요.");
                        } else {
                            CommentRegisterRequest commentRegisterRequest = new CommentRegisterRequest(commentFinal,starRateScore);
                            initMyApi.requestRegisterComment(getPreferenceString("accessToken"), bookId,commentRegisterRequest).enqueue(new Callback<ResponseMessage>() {
                                @Override
                                public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        ResponseMessage result = response.body();
                                        if (result.getErrorCode().equals("E0008")) {
                                            showAlert("리뷰가 작성되었습니다!");
                                            initCommentList(bookId);
                                        }
                                    }
                                }

                                @Override
                                public void onFailure(Call<ResponseMessage> call, Throwable t) {
                                    t.printStackTrace();

                                }
                            });
                        }



                    }

                    @Override
                    public void onNegativeClick() {

                    }
                });
                customDialog.show();
            }
        }
    }

    public void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setTitle("알림")
                .setMessage(message)
                .setPositiveButton("확인", null)
                .create()
                .show();
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
