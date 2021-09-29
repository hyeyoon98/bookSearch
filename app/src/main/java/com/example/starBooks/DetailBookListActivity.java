package com.example.starBooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.APIInterface.initMyApi;
import com.example.starBooks.databinding.ActivityDetailbooklistBinding;
import com.example.starBooks.dto.Book;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailBookListActivity extends FragmentActivity {

    private ViewPager2 viewPager2;
    private FragmentStateAdapter pagerAdapter;

    public final String DATA_STORE = "DATA_STORE";
    private final int NUM_PAGES = 2;

    private ActivityDetailbooklistBinding binding;
    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_detailbooklist);

        Intent intent = getIntent();
        int getBookId = intent.getExtras().getInt("book_id");



        Bundle bundle = new Bundle();
        bundle.putInt("bookId", getBookId);
        /*informationFragment.setArguments(bundle);*/
        getSupportFragmentManager().setFragmentResult("bookId", bundle);

        System.out.println("bookId_detail" + bundle);


        pagerAdapter = new viewPagerAdapter(this);
        binding.viewPager2.setAdapter(pagerAdapter);
        binding.viewPager2.setCurrentItem(0);



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


    public void setPreference(String key, int value){
        SharedPreferences pref = getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt(key, value);
        editor.apply();
    }

   /* private class viewPagerAdapter extends RecyclerView.Adapter {
        public viewPagerAdapter(DetailBookListFragment detailBookListFragment) {
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            holder

        }

        @Override
        public int getItemCount() {
            return NUM_PAGES;
        }
    }*/
}
