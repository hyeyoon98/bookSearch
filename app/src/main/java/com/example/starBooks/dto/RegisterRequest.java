package com.example.starBooks.dto;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    @SerializedName("userName")
    public String userName;

    @SerializedName("userId")
    public String userId;

    @SerializedName("password")
    public String password;

    @SerializedName("passwordConfirm")
    public String passwordConfirm;

    @SerializedName("phoneNumber")
    public String phoneNumber;

    @SerializedName("email")
    public String email;

    @SerializedName("birthDate")
    public int birthDate;

    public String getUserName() {
        return userName;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public int getBirthDate() {
        return birthDate;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(int birthDate) {
        this.birthDate = birthDate;
    }

    public RegisterRequest(String userName, String userId, String password, String passwordConfirm, String phoneNumber, String email, int birthDate) {
        this.userName = userName;
        this.userId = userId;
        this.password = password;
        this.passwordConfirm = passwordConfirm;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.birthDate = birthDate;

    }

    @Override
    public String toString() {
        return "RegisterRequest{" +
                "userName='" + userName + '\'' +
                ", userId='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", passwordConfirm='" + passwordConfirm + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", email='" + email + '\'' +
                ", birthDate='" + birthDate + '\'' +
                '}';
    }
}
