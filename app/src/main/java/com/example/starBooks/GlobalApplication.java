package com.example.starBooks;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //SDK 초기화
        KakaoSdk.init(this, "{NATIVE_APP_KEY}");
    }
}
