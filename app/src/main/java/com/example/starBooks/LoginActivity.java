package com.example.starBooks;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.APIInterface.initMyApi;
import com.example.starBooks.dto.LoginRequest;
import com.example.starBooks.dto.LoginResponse;
import com.example.starBooks.databinding.ActivityLoginBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;

    ActivityLoginBinding binding;
    public final String DATA_STORE = "DATA_STORE";
    String autoLoginId = "autoLoginId";
    String autoLoginPw = "autoLoginPw";
    private BackPressCloseHandler backPressCloseHandler;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setOnClick(this);
        backPressCloseHandler = new BackPressCloseHandler(this);

        if (!getPreferenceString(autoLoginId).equals("") && !getPreferenceString(autoLoginPw).equals("")) {
            binding.autoLogin.setChecked(true);
            checkAutoLogin(getPreferenceString(autoLoginId));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
                if (binding.insertId.getText().toString().trim().length() == 0 || binding.insertPassword.getText().toString().trim().length() == 0) {
                    showAlert("로그인 정보를 모두 입력바랍니다.");
                } else {
                    LoginRequest();
                }


                break;

            case R.id.btn_register:
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;

        }

    }

    public void LoginRequest() {

        String userId = binding.insertId.getText().toString().trim();
        String userPw = binding.insertPassword.getText().toString().trim();

        LoginRequest loginRequest = new LoginRequest(userId, userPw);

        retrofitClient  = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        initMyApi.getLoginResponse(loginRequest).enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    LoginResponse result = response.body();

                    System.out.println("로그인리스폰스 >>>>>>>>>>"+result);
                    String code = result.getCode();
                    String msg = result.getMessage();
                    System.out.println("메세지 >>>>>>>>>>"+msg);
                    String token = result.getToken();


                    switch (code) {
                        case "E0001":
                            showToast("클라이언트 에러");
                            break;
                        case "E0002":
                            showToast("잘못된 접근방식입니다.");
                            break;
                        case "E0003":
                            showToast("서버 에러");
                        case "E0006" :
                            showToast(msg);

                        case "E0007" :
                            showToast(msg);

                        case "E0008" :
                            setPreference("token", token);
                            showToast("반갑습니다");
                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            finish();
                            break;
                    }

                    
                }
            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {

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
        return pref.getString(key, "");
    }

    public void checkAutoLogin(String id){

        //Toast.makeText(this, id + "님 환영합니다.", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        View focusView = getCurrentFocus();
        if (focusView != null) {
            Rect rect = new Rect();
            focusView.getGlobalVisibleRect(rect);
            int x = (int) ev.getX(), y = (int) ev.getY();
            if (!rect.contains(x, y)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                if (imm != null)
                    imm.hideSoftInputFromWindow(focusView.getWindowToken(), 0);
                focusView.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override public void onBackPressed() {
        //super.onBackPressed();
        backPressCloseHandler.onBackPressed();
    }

    public void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
        builder.setTitle("알림")
                .setMessage(message)
                .setPositiveButton("확인", null)
                .create()
                .show();
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }


}
