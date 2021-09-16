package com.example.starBooks;

import android.app.Application;

import com.kakao.sdk.common.KakaoSdk;

import org.conscrypt.Conscrypt;

import java.security.Security;

public class GlobalApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Security.insertProviderAt(Conscrypt.newProvider(),1);
    }
}
