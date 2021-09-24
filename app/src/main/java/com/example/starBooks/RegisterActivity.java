package com.example.starBooks;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.DatePicker;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.starBooks.APIInterface.RetrofitClient;
import com.example.starBooks.APIInterface.initMyApi;
import com.example.starBooks.dto.CheckIdResponse;
import com.example.starBooks.dto.RegisterRequest;
import com.example.starBooks.databinding.ActivityRegisterBinding;
import com.example.starBooks.dto.RegisterResponse;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private RetrofitClient retrofitClient;
    private initMyApi initMyApi;

    private int year = 0, month = 0, day = 0;

    ActivityRegisterBinding binding;
    public final String DATA_STORE = "DATA_STORE";



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_register);
        binding.setTitle("회원가입");
        binding.setOnClick(this);
    }

    @Override
    public void onClick(View v) {
        String userName = binding.insertName.getText().toString();
        String userId = binding.insertId.getText().toString();
        String userPw = binding.insertPassword.getText().toString();
        String userPwConfirm = binding.insertCheckPw.getText().toString();
        String userPhoneNum = binding.insertPhoneNum.getText().toString();
        String userEmail = binding.insertEmail.getText().toString();
        int userBirth = binding.insertBirth.getText().length();

        if (v.getId()==R.id.start_button) {
            if (userName.trim().length() == 0 || userPw.trim().length() ==0 || userId.trim().length() ==0
                    || userPwConfirm.trim().length() ==0 || userPhoneNum.trim().length() ==0 || userEmail.trim().length() == 0 || userBirth == 0) {
                showAlert("정보를 모두 입력해주시길 바랍니다.");
            } else if (userPw.trim().length() < 8 || userPw.trim().length() >= 16) {
                showAlert("비밀번호는 8자리~15자리로 입력바랍니다.");
            } else if (hasSpecialCharacter(userPw)==false) {
                showAlert("비밀번호에 특수문자를 포함해주시길 바랍니다.");
            } else if (!userPw.equals(userPwConfirm)) {
                showAlert("비밀번호가 일치하지 않습니다.");
            } else if (userPhoneNum.trim().length() != 11) {
                showAlert("전화번호가 맞는지 다시 확인바랍니다.");
            } else if (hasSpecialCharacter(userEmail)==false) {
                showAlert("이메일 형식이 맞지 않습니다.");
            } else {
                RequestRegister();
            }
        } else if (v.getId() == R.id.insert_birth) {
            final Calendar calendar = Calendar.getInstance();
            year = calendar.get(Calendar.YEAR);
            month = calendar.get(Calendar.MONTH);
            day = calendar.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    binding.insertBirth.setText(String.format("%d%02d%d",year,month+1,dayOfMonth));
                }
            }, year, month, day);
            datePickerDialog.show();
        } else if (v.getId() == R.id.check_id) {
            if (binding.insertId.getText().toString().trim() == null) {
                RequestCheckId();
            } else {
                showAlert("아이디를 입력해주세요.111111");
            }
        }
    }

    public void RequestRegister() {
        String userName = binding.insertName.getText().toString().trim();
        String userId = binding.insertId.getText().toString().trim();
        String userPw = binding.insertPassword.getText().toString().trim();
        String userPwConfirm = binding.insertCheckPw.getText().toString().trim();
        String userPhoneNum = binding.insertPhoneNum.getText().toString().trim();
        String userEmail = binding.insertEmail.getText().toString().trim();
        String userBirth = binding.insertBirth.getText().toString();

        RegisterRequest registerRequest = new RegisterRequest(userName, userId, userPw, userPwConfirm, userPhoneNum, userEmail, Integer.parseInt(userBirth));

        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        initMyApi.getSignUp(registerRequest).enqueue(new Callback<RegisterResponse>() {
            @Override
            public void onResponse(Call<RegisterResponse> call, Response<RegisterResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    RegisterResponse result = response.body();
                    String code = result.getCode();

                    if (code.equals("E0008") && getPreferenceString("checkId").equals("E0008")) {
                        Toast.makeText(RegisterActivity.this,"회원가입이 완료되었습니다.",Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            /*        intent.putExtra(userId, userId);
                    intent.putExtra(userPw, userPw);*/
                        startActivity(intent);
                        finish();
                    } else if (getPreferenceString("checkId").equals("E0004")) {
                        showAlert("아이디 중복확인을 확인해주세요");
                    }

                }
            }

            @Override
            public void onFailure(Call<RegisterResponse> call, Throwable t) {

            }
        });



    }

    public void RequestCheckId() {
        String userId = binding.insertId.getText().toString().trim();

        retrofitClient = RetrofitClient.getInstance();
        initMyApi = RetrofitClient.getRetrofitInterface();

        initMyApi.getCheckId(userId).enqueue(new Callback<CheckIdResponse>() {
            @Override
            public void onResponse(Call<CheckIdResponse> call, Response<CheckIdResponse> response) {

                if (response.body() != null) {
                    CheckIdResponse result = response.body();
                    String code = result.getCode();
                    String msg = result.getMessage();

                    if (response.code() == 200) {

                        Log.e("TAG", "Code 200");

                        if (code.equals("E0008")) {
                            showToast(msg);
                            binding.insertId.setInputType(InputType.TYPE_NULL);
                        }

                    } else if (response.code() == 400 ) {
                        Log.d("TAG", "Code 400");
                        showAlert("중복된 아이디입니다");
                    }

                    setPreference("checkId", code);
                }



            }

            @Override
            public void onFailure(Call<CheckIdResponse> call, Throwable t) {


                Log.d("TAG", "ErrorCode 400");



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

    //화면 터치 시 키보드 내려감
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

    public void showAlert(String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
        builder.setTitle("알림")
                .setMessage(message)
                .setPositiveButton("확인", null)
                .create()
                .show();
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }

    public static boolean hasSpecialCharacter(String string) {
        if (TextUtils.isEmpty(string)) {
            return false;
        }

        for (int i = 0; i < string.length(); i++) {
            if (!Character.isLetterOrDigit(string.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

}
