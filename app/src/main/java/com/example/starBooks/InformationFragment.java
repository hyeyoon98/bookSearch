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

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.databinding.FragmentInformationBinding;
import com.example.starBooks.dto.Book;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.content.Context.MODE_PRIVATE;

public class InformationFragment extends Fragment {

    FragmentInformationBinding binding;

    public final String DATA_STORE = "DATA_STORE";
    private RetrofitClient retrofitClient;
    private com.example.starBooks.APIInterface.initMyApi initMyApi;

    private int bookId;

    private static final String BUNDLE_PARAM = "book_Id";

    public static InformationFragment newInstance(int bookId) {
        Bundle bundle = new Bundle();
        InformationFragment fragment = new InformationFragment();
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

        initView(bookId);


        /*getParentFragmentManager().setFragmentResultListener("bookId", this, new FragmentResultListener() {
            @Override public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle)
            {
                if (bundle.getInt("bookId") != 0) {
                    bookId = bundle.getInt("bookId");
                    System.out.println("----------------------------"+bookId);
                    initView(bookId);
                }

            }
        });*/
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_information,container,false);
        View root = binding.getRoot();


        return root;
    }

    public void initView(int bookId) {
        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        initMyApi.getDetailBookList(bookId).enqueue(new Callback<Book>() {
            @Override
            public void onResponse(Call<Book> call, Response<Book> response) {
                if (response.isSuccessful() && response.body() != null) {
                    System.out.println("detailbook_fragment >>>>" + response.body());
                    Book result = response.body();
                    binding.tvDescription.setText(result.getDescription());

                }
            }

            @Override
            public void onFailure(Call<Book> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



    public int getPreferenceInt(String key) {
        SharedPreferences pref = getContext().getSharedPreferences(DATA_STORE, MODE_PRIVATE);
        return pref.getInt(key, 0);
    }
}
