package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

import java.util.Date;

public class tokenResponseDto {

    public String getGrantType() {
        return grantType;
    }

    public void setGrantType(String grantType) {
        this.grantType = grantType;
    }

    public String getAccessTokenExpiresIn() {
        return accessTokenExpiresIn;
    }

    public void setAccessTokenExpiresIn(String accessTokenExpiresIn) {
        this.accessTokenExpiresIn = accessTokenExpiresIn;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    @SerializedName("grantType")
    public String grantType;

    @SerializedName("accessTokenExpiresIn")
    public String accessTokenExpiresIn;

    @SerializedName("accessToken")
    public String accessToken;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    @SerializedName("refreshToken")
    public String refreshToken;

    @Override
    public String toString() {
        return "tokenResponseDto{" +
                "grantType='" + grantType + '\'' +
                ", accessTokenExpiresIn='" + accessTokenExpiresIn + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                '}';
    }
}
