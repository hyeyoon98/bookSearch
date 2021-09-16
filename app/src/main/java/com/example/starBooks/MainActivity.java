package com.example.starBooks;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.BindingAdapter;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.APIInterface.initMyApi;
import com.example.starBooks.ListView.ListViewAdapter;
import com.example.starBooks.dto.Book;
import com.example.starBooks.dto.Page;
import com.example.starBooks.databinding.ActivityMainBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MainActivity extends AppCompatActivity {

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;
    private ActivityMainBinding binding;
    private ListViewAdapter adapter;
    private List<Book> bookList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(this, R.layout.activity_main);
        initView();


        /*spinner*/
        //어댑터 생성
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_array,R.layout.spinner_layout);
        //드롭다운뷰 연결
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        //UI와 연결
        binding.homeSpinner.setAdapter(adapter);
    }

    public void initView(){
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        initMyApi.getPageResponse("createAt", 1,10).enqueue(new Callback<Page>() {
            @Override
            public void onResponse(Call<Page> call, Response<Page> response) {
                System.out.println("성공??>>>"+response);
                if (response.isSuccessful()&&response.body()!=null) {
                    Page result = response.body();
                    System.out.println("이거 되나요??>>>"+result);
                    bookList = result.getContent();
                    binding.swipeLayout.setRefreshing(false);
                    parsingJSONData(bookList);
                }
            }

            @Override
            public void onFailure(Call<Page> call, Throwable t) {
                Toast.makeText(MainActivity.this, "불러오기 실패하셨네요 ㅋㅋ", Toast.LENGTH_SHORT);
            }
        });

    }

    private void parsingJSONData(List data) {
        System.out.println("리스트 가져와 >>>>>>>>>>>" + data);
        List<Book> bookList = new ArrayList<>();

        try {
            JSONArray jArray = new JSONArray(data);
            if (jArray.length()==0) {
                Toast.makeText(this, "불러오기 실패하셨네요 ㅋㅋ", Toast.LENGTH_SHORT);
                // 주문 내역이 비었습니다.
            } else {
                for(int i = 0; i < jArray.length(); i++) {
                    Book book = new Book();
                    JSONObject jObject = jArray.getJSONObject(i);

                    //마켓 이름 저장
                    book.setId(jObject.getInt("id"));
                    book.setImgUrl(jObject.getString("imgUrl"));
                    book.setTitle(jObject.getString("title"));
                    book.setAuthor(jObject.getString("author"));
                    book.setPublisher(jObject.getString("publisher"));
                    book.setPrice(jObject.getInt("price"));
                    book.setCreateAt(jObject.getString("createAt"));
                    book.setDescription(jObject.getString("description"));
                    bookList.add(book);
                }
                binding.setListAdapter(new ListViewAdapter(bookList));

            }


        } catch(JSONException e) {
            e.printStackTrace();
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