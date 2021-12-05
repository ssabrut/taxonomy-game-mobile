package com.taxon_mobile.models;

import com.google.gson.Gson;

public class LogoutResponse {

    private String status_message;
    private int status;

    public static LogoutResponse objectFromData(String str) {

        return new Gson().fromJson(str, LogoutResponse.class);
    }

    public String getStatus_message() {
        return status_message;
    }

    public void setStatus_message(String status_message) {
        this.status_message = status_message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
