package com.example.starBooks;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.APIInterface.initMyApi;
import com.example.starBooks.adapter.MainAdapter;
import com.example.starBooks.dto.Book;
import com.example.starBooks.dto.Page;
import com.example.starBooks.databinding.ActivityMainBinding;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    public RetrofitClient retrofitClient;
    public initMyApi initMyApi;
    public ActivityMainBinding binding;
    public MainAdapter adapter;
    public List<Book> bookList_list = new ArrayList<>();

    public final String DATA_STORE = "DATA_STORE";


    //page
    private String sort = "createAt";
    private int page = 1;
    private final int size = 10;


    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        backPressCloseHandler = new BackPressCloseHandler(this);

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        initView(page, size);

        System.out.println("저장된 accessToken >>>" + getPreferenceString("accessToken") );




        //Floating Button
        binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPreferenceString("accessToken") == null ) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("알림")
                            .setMessage("현재 비회원 상태입니다. 로그인하시겠습니까?")
                            .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Intent intent = new Intent(MainActivity.this, LoginActivity.class);
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
        });

        /*binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int pastPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findFirstVisibleItemPosition();
                int lastPosition = ((LinearLayoutManager)recyclerView.getLayoutManager()).findLastVisibleItemPosition();
                int totalCount = recyclerView.getAdapter().getItemCount();

                if (lastPosition == totalCount) {
                    page++;
                    binding.progressBar.setVisibility(View.VISIBLE);
                    initView(page, size);
                }
            }
        });*/


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    System.out.println("page넘버 >>>" + page);
                    if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                        ++page;
                        /*binding.progressBar.setVisibility(View.VISIBLE);*/
                        adapter.notifyDataSetChanged();
                        initView(page, size);
                    } else if (page == 0) {
                        showToast("첫 페이지입니다");
                    } else if (scrollY==0 && page > 0){
                        --page;
                        /*binding.progressBar.setVisibility(View.VISIBLE);*/
                        adapter.notifyDataSetChanged();
                        initView(page, size);
                    }
                }
            });
        }

        /*spinner*/
        //어댑터 생성
        ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.spinner_array,R.layout.spinner_layout);
        //드롭다운뷰 연결
        spinnerAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //UI와 연결
        binding.homeSpinner.setAdapter(spinnerAdapter);
    }

    public void initView(int page, int size){
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        initMyApi.getPageResponse(sort, page,size).enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call,Response<Page> response) {
                if (response.isSuccessful() && response.body() != null) {


                    System.out.println("response >>>" + response);

                    Page result = response.body();
                    bookList_list = result.getContent();

                    adapter = new MainAdapter(MainActivity.this, bookList_list);
                    binding.recyclerView.setAdapter(adapter);

                }
            }

            @Override
            public void onFailure(@NotNull Call<Page> call, Throwable t) {
                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
        });

    }


    //Spinner Listener
    public void spinnerListener() {
        binding.homeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //선택 시 작동기능
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        sort = "createAt";
                        initView(page, size);
                        break;
                    case 2:
                        sort = "heart";
                        initView(page, size);
                        break;
                    case 3:
                        sort = "starRate";
                        initView(page, size);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    public void setPreference(String key, String value){
        SharedPreferences pref = getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getPreferenceString(String key) {
        SharedPreferences pref = getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        return pref.getString(key, null);
    }

    public void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        builder.setTitle("알림")
                .setMessage(message)
                .setPositiveButton("확인", null)
                .create()
                .show();
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    //뒤로가기 버튼 2회 클릭 시 종료
    @Override public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}