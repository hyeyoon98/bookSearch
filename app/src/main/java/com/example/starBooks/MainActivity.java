package com.example.starBooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.widget.NestedScrollView;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;
    private ActivityMainBinding binding;
    private MainAdapter adapter;
    private ArrayList<Book> bookList_list = new ArrayList<>();

    private int page = 1;
    private int size = 10;
    private boolean lastItemVisibleFlag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);

        adapter = new MainAdapter(this, bookList_list);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.setAdapter(adapter);

        initView(page, size);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.scrollView.setOnScrollChangeListener(new NestedScrollView.OnScrollChangeListener() {

                @Override
                public void onScrollChange(NestedScrollView v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                    if (scrollY == v.getChildAt(0).getMeasuredHeight() - v.getMeasuredHeight()) {
                        page++;
                        binding.progressBar.setVisibility(View.VISIBLE);
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

        initMyApi.getPageResponse("createAt", page,size).enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                if (response.isSuccessful()&&response.body()!=null) {

                    binding.progressBar.setVisibility(View.GONE);

                    try {
                        JSONArray jsonArray = new JSONArray(response.body().getContent());
                        parseResult(jsonArray);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT);
            }
        });

    }

    private void parseResult(JSONArray jsonArray) {
        for (int i = 0; i < jsonArray.length(); i++)
        {
            try
            {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                Book book = new Book();
                book.setId(jsonObject.getInt("id"));
                book.setImgUrl(jsonObject.getString("imgUrl"));
                book.setTitle(jsonObject.getString("title"));
                book.setAuthor(jsonObject.getString("author"));
                book.setPublisher(jsonObject.getString("publisher"));
                book.setPrice(jsonObject.getInt("price"));
                book.setCreateAt(jsonObject.getString("createAt"));
                book.setDescription(jsonObject.getString("description"));
                book.setIsbn(jsonObject.getString("isbn"));
                book.setModifiedAt(jsonObject.getString("modifiedAt"));
                bookList_list.add(book);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
            adapter = new MainAdapter(MainActivity.this, bookList_list);
            binding.recyclerView.setAdapter(adapter);
            adapter.notifyDataSetChanged();
        }
    }



    //Spinner Listener
    public void spinnerListener() {
        binding.homeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            //선택 시 작동기능
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 1:
                        break;
                    case 2:
                        break;
                    case 3:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }



}