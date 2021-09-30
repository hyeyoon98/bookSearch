package com.example.starBooks;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.APIInterface.initMyApi;
import com.example.starBooks.databinding.ActivityDetailbooklistBinding;
import com.example.starBooks.dto.Book;
import com.example.starBooks.dto.HeartResponse;
import com.example.starBooks.dto.ResponseMessage;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import javax.xml.transform.Result;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBookListActivity extends FragmentActivity implements View.OnClickListener {

    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;

    public final String DATA_STORE = "DATA_STORE";
    private final int NUM_PAGES = 2;

    private ActivityDetailbooklistBinding binding;
    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;
    private int getBookId;
    private boolean isHeart = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailbooklist);

        Intent intent = getIntent();
        getBookId = intent.getExtras().getInt("book_id");

        System.out.println("bookId_detail >>>" + getBookId);



        Bundle bundle = new Bundle();
        bundle.putInt("bookId", getBookId);
        /*informationFragment.setArguments(bundle);*/
        getSupportFragmentManager().setFragmentResult("bookId", bundle);

        System.out.println("bookId_detail" + bundle);


        pagerAdapter = new viewPagerAdapter(this);
        binding.viewPager2.setAdapter(pagerAdapter);
        binding.viewPager2.setCurrentItem(0);

        binding.buttonHeart.setOnClickListener(this);



        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(binding.tabLayout, binding.viewPager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                if(position == 0) {
                    tab.setText("책소개");
                } else {
                    tab.setText("리뷰/평점");
                }
            }
        });
        tabLayoutMediator.attach();




        initView(getBookId);
    }

    public void initView(int bookId) {
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        initMyApi.getDetailBookList(bookId).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("detailbook >>>>" + response.body());
                    Book book = response.body();
                    binding.setBook(book);
                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
            }
        });

        if (getPreferenceString("accessToken") != null) {
            String authToken = "Bearer "+getPreferenceString("accessToken");

            initMyApi.getHeart(authToken, bookId).enqueue(new Callback<HeartResponse>() {
                @Override
                public void onResponse(Call<HeartResponse> call, Response<HeartResponse> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        HeartResponse result = response.body();

                        if (result.code.equals("E0008")) {
                            isHeart = result.map.getIsHeart();
                            if (isHeart) {
                                binding.buttonHeart.setBackgroundResource(R.drawable.ic_heart_solid);
                            }
                        }

                    }
                }

                @Override
                public void onFailure(Call<HeartResponse> call, Throwable t) {
                    t.printStackTrace();

                }
            });
        }
    }



    private  class viewPagerAdapter extends FragmentStateAdapter {

        public viewPagerAdapter(FragmentActivity fragmentActivity) {
            super(fragmentActivity);
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            if (position == 0) {
                return InformationFragment.newInstance();
            } else {
                return CommentFragment.newInstance();
            }
        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_heart) {
            System.out.println("버튼 눌렀을 때 accessToken 있습니까? >>" + getPreferenceString("accessToken"));
            if (getPreferenceString("accessToken") == null ) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DetailBookListActivity.this);
                builder.setTitle("알림")
                        .setMessage("현재 비회원 상태입니다. 로그인하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(DetailBookListActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                        })
                        .setNegativeButton("취소", null)
                        .create()
                        .show();
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                alertDialog.dismiss();
            } else if (getPreferenceString("accessToken") != null){
                if (isHeart == true) {
                    deleteHeart(getBookId);
                    System.out.println("bookId_detail_deleteHeart >>>" + getBookId);

                } else {
                    pressHeart(getBookId);
                    System.out.println("bookId_detail_pressHeart >>>" + getBookId);

                }

            }

        }
    }

    public void pressHeart(int bookId) {
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        String authToken = "Bearer "+getPreferenceString("accessToken");

        System.out.println("accessToken 있습니까? >>" + getPreferenceString("accessToken"));

        initMyApi.clickHeart(authToken, bookId).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.isSuccessful() && response.body() != null) {

                    ResponseMessage result = response.body();

                    if (result.errorCode.equals("E0008")) {

                        binding.buttonHeart.setBackgroundResource(R.drawable.ic_heart_solid);
                        isHeart = true;
                    }
                } else if (response.code() == 401) {
                    deletePreference("accessToken");
                    deletePreference("refreshToken");


                    AlertDialog.Builder builder = new AlertDialog.Builder(DetailBookListActivity.this);
                    builder.setTitle("알림")
                            .setMessage("로그인이 만료되었습니다. 다시 로그인하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(DetailBookListActivity.this, LoginActivity.class);
                                    startActivity(intent);
                                }
                            })
                            .setNegativeButton("취소", null)
                            .create()
                            .show();
                    AlertDialog alertDialog = builder.create();
                    alertDialog.show();
                    alertDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                t.printStackTrace();

            }
        });
    }

    public void deleteHeart(int bookId) {
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        String authToken = "Bearer "+getPreferenceString("accessToken");

        initMyApi.requestDeleteHeart(authToken, bookId).enqueue(new Callback<ResponseMessage>() {
            @Override
            public void onResponse(Call<ResponseMessage> call, Response<ResponseMessage> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ResponseMessage result = response.body();
                    if (result.errorCode.equals("E0008")) {
                        binding.buttonHeart.setBackgroundResource(R.drawable.ic_heart_regular);
                        isHeart = false;
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseMessage> call, Throwable t) {
                t.printStackTrace();

            }
        });

    }


    public void setPreference(String key, int value){
        SharedPreferences pref = getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        return pref.getString(key, null);
    }

    public void deletePreference(String key) {
        SharedPreferences pref = getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.remove(key);
        editor.commit();
    }

    public void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DetailBookListActivity.this);
        builder.setTitle("알림")
                .setMessage(message)
                .setPositiveButton("확인", null)
                .create()
                .show();
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}
